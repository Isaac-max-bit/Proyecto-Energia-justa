import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { EnergyService } from '../../../services/energy.service/energy.service';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { Chart } from 'chart.js/auto'; 
import { forkJoin } from 'rxjs';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @ViewChild('miGrafica') canvasBarra!: ElementRef;
  @ViewChild('graficaTorta') canvasTorta!: ElementRef;
  @ViewChild('graficaTendencia') canvasTendencia!: ElementRef;

  // Instancias de Chart.js
  chartBarra: any;
  chartTorta: any;
  chartTendencia: any;

  listaCompleta: any[] = []; 
  listaFiltrada: any[] = [];
  filtro: string = '';
  yearActual: number = 2021; // Año inicial

  // Datos específicos para las gráficas
  datosBarra: any[] = [];
  datosTorta: any[] = [];
  datosTendencia: any[] = [];
  datosProduccionTabla: any[] = [];

  constructor(private energyService: EnergyService, private cDRef : ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargarTodo();
  }


    cargarTodo() {
    forkJoin({
        listaGeneral: this.energyService.getEnergy(),
        topEolica: this.energyService.getTopEolica(this.yearActual),
        participacion: this.energyService.getParticipacion(this.yearActual),
        tendencia: this.energyService.getTendenciaSolar(),
        produccionTabla: this.energyService.getProduccion(this.yearActual)
    }).subscribe({
        next: (res) => {
        console.log('Datos Barra:', res.topEolica);
        console.log('Datos Torta:', res.participacion);
        console.log('Datos Tendencia:', res.tendencia);
        this.listaCompleta = res.listaGeneral;
        this.datosBarra = res.topEolica;
        this.datosTorta = res.participacion;
        this.datosTendencia = res.tendencia;
        this.datosProduccionTabla = res.produccionTabla;
        
        this.filtrarDatos();

        this.cDRef.detectChanges(); // Fuerza a Angular a renderizar los <canvas>

        setTimeout(() => {
            this.generarGraficas();
        }, 0); 
        },
        error: (err) => console.error('Error:', err)
    });
    }
    get resumenProduccionAnual() {
    if (!this.filtro) return null;
    
    const busqueda = this.filtro.toLowerCase().trim();
    return this.datosProduccionTabla.find(item => 
        item.region.toLowerCase().includes(busqueda)
    );
    }

  filtrarDatos() {
    const busqueda = this.filtro.toLowerCase().trim();
    this.listaFiltrada = this.listaCompleta.filter(item => 
      item.entity?.toLowerCase().includes(busqueda)
    );
  }

calcularProduccionTotal(): string {
  // 1. Solo tomamos los datos que coincidan con el año que el usuario ve
  // 2. Solo tomamos los que tengan código ISO (países)
  const datosLimpios = this.listaCompleta.filter(item => 
    item.dataYear === this.yearActual &&   // Filtro de año (usa el nombre de propiedad de tu interface)
    item.code && item.code.trim() !== '' && // Solo países
    item.code !== 'OWID_WRL'                // Excluir total mundial manual
  );

  // 3. Si hay un filtro de búsqueda, filtramos sobre esos datos limpios
  const busqueda = this.filtro.toLowerCase().trim();
  const datosFinales = busqueda 
    ? datosLimpios.filter(item => item.entity.toLowerCase().includes(busqueda))
    : datosLimpios;

  const total = datosFinales.reduce((acc, item) => {
    return acc + (Number(item.hydroTwh) || 0) + 
                 (Number(item.solarTwh) || 0) + 
                 (Number(item.windTwh) || 0);
  }, 0);

  return total.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

calcularPorcentajeRegion(): string {
  // 1. Verificamos que existan datos cargados
  if (!this.datosTorta || this.datosTorta.length === 0) {
    return '0.00';
  }

  // 2. Determinamos qué lista usar: la completa o una filtrada
  let listaParaCalcular: any[];
  const busqueda = this.filtro?.toLowerCase().trim();

  if (!busqueda) {
    // Si no hay filtro, usamos todas las regiones (Promedio Global)
    listaParaCalcular = this.datosTorta;
  } else {
    // Si hay filtro, buscamos las coincidencias (ej: "Africa")
    listaParaCalcular = this.datosTorta.filter(item => {
      if (!item.region) return false;
      // Normalizamos el texto eliminando espacios especiales (&nbsp;)
      const regionLimpia = item.region.replace(/\u00A0/g, ' ').toLowerCase();
      return regionLimpia.includes(busqueda);
    });
  }

  // 3. Si después de filtrar no hay resultados, retornamos 0
  if (listaParaCalcular.length === 0) {
    return '0.00';
  }

  // 4. Calculamos el promedio sobre la lista seleccionada
  const suma = listaParaCalcular.reduce((acc, item) => {
    // Usamos el nombre exacto de la propiedad de tu JSON
    const valor = Number(item.porcentaje_renovable);
    return acc + (isNaN(valor) ? 0 : valor);
  }, 0);

  const promedio = suma / listaParaCalcular.length;

  // 5. Retornamos el valor con 2 decimales
  return promedio.toFixed(2);
}

  // --- LÓGICA DE GRÁFICAS ---

  generarGraficas() {
    this.destruirGraficas();
    this.renderBarChart();
    this.renderPieChart();
    this.renderLineChart();
  }

  private destruirGraficas() {
    if (this.chartBarra) this.chartBarra.destroy();
    if (this.chartTorta) this.chartTorta.destroy();
    if (this.chartTendencia) this.chartTendencia.destroy();
  }

  private renderBarChart() {
    if (!this.canvasBarra?.nativeElement) return;
    this.chartBarra = new Chart(this.canvasBarra.nativeElement, {
      type: 'bar',
      data: {
        labels: this.datosBarra.map(item => item.pais),
        datasets: [{ label: 'Viento (TWh)', data: this.datosBarra.map(item => item.produccion_eolica_twh), backgroundColor: '#10b981' }]
      },
      options: { responsive: true, maintainAspectRatio: false }
    });
  }

private renderPieChart() {
  if (!this.canvasTorta?.nativeElement || this.datosTorta.length === 0) return;

  const coloresDinamicos = this.generarPaletaColores(this.datosTorta.length);

  this.chartTorta = new Chart(this.canvasTorta.nativeElement, {
    type: 'doughnut',
    data: {
      labels: this.datosTorta.map(item => item.region), 
      datasets: [{
        data: this.datosTorta.map(item => item.porcentaje_renovable || 0),
        backgroundColor: coloresDinamicos,
        hoverOffset: 20
      }]
    },
    options: { 
      responsive: true, 
      maintainAspectRatio: false,
      plugins: {
        legend: { display: false }, // Ocultamos leyenda por exceso de datos
        tooltip: {
          callbacks: {
            label: (context) => {
              const label = context.label || '';
              const value = context.parsed;
              return `${label}: ${value.toFixed(2)}%`;
            }
          }
        }
      }
    }
  });
}

  private renderLineChart() {
    if (!this.canvasTendencia?.nativeElement) return;
    this.chartTendencia = new Chart(this.canvasTendencia.nativeElement, {
      type: 'line',
      data: {
        labels: this.datosTendencia.map(item => item.anio),
        datasets: [{
          label: 'Capacidad Solar Global',
          data: this.datosTendencia.map(item => item.capacidad_solar_global),
          borderColor: '#fbbf24',
          fill: true,
          tension: 0.3
        }]
      },
      options: { responsive: true, maintainAspectRatio: false }
    });
  }
  
  private generarPaletaColores(cantidad: number): string[] {
  const colores = [];
  for (let i = 0; i < cantidad; i++) {
    // Distribuimos el tono (hue) de 0 a 360
    const hue = (i * (360 / cantidad)) % 360;
    // Usamos una saturación del 70% y luminosidad del 60% para que sean colores vivos
    colores.push(`hsl(${hue}, 70%, 60%)`);
  }
  return colores;
}
}