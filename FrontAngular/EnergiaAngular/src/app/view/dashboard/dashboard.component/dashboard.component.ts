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
  // Referencias a los canvas del HTML
  @ViewChild('miGrafica') canvasBarra!: ElementRef;
  @ViewChild('graficaTorta') canvasTorta!: ElementRef;
  @ViewChild('graficaTendencia') canvasTendencia!: ElementRef;

  // Instancias de Chart.js
  chartBarra: any;
  chartTorta: any;
  chartTendencia: any;

  // Variables vinculadas al HTML (ngModel y cálculos)
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

  // Carga principal: alimenta la tabla y las gráficas
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

        // PASO CRÍTICO:
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
    // Buscamos coincidencia exacta o que contenga el nombre
    return this.datosProduccionTabla.find(item => 
        item.region.toLowerCase().includes(busqueda)
    );
    }
  // --- MÉTODOS QUE PIDE TU HTML ---

  filtrarDatos() {
    const busqueda = this.filtro.toLowerCase().trim();
    this.listaFiltrada = this.listaCompleta.filter(item => 
      item.entity?.toLowerCase().includes(busqueda)
    );
  }

  calcularProduccionTotal(): string {
    const total = this.listaFiltrada.reduce((acc, item) => {
      return acc + (Number(item.hydroTwh) || 0) + (Number(item.solarTwh) || 0) + (Number(item.windTwh) || 0);
    }, 0);
    return total.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
  }

  calcularPorcentajeRegion(): string {
    if (this.listaFiltrada.length === 0) return '0.00';
    // Ejemplo: Promedio de participación (puedes ajustar la lógica según tu API)
    return (this.listaFiltrada.length / 1.5).toFixed(2); 
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
    type: 'doughnut', // Te sugiero doughnut para que se vea más moderno
    data: {
      labels: this.datosTorta.map(item => item.region), 
      datasets: [{
        data: this.datosTorta.map(item => item.porcentaje_renovable || 0),
        backgroundColor: coloresDinamicos,
        hoverOffset: 20 // Esto hace que la porción resalte al pasar el mouse
      }]
    },
    options: { 
      responsive: true, 
      maintainAspectRatio: false,
      plugins: {
        legend: { display: false }, // Ocultamos leyenda por exceso de datos
        tooltip: {
          callbacks: {
            // Aquí añadimos el símbolo de porcentaje
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