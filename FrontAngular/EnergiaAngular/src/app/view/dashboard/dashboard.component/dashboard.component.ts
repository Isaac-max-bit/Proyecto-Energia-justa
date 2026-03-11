import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { EnergyService } from '../../../services/energy.service/energy.service';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { Chart } from 'chart.js/auto'; 

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
  
  chartBarra: any;
  chartTorta: any;
  chartTendencia: any;

  listaCompleta: any[] = []; 
  listaFiltrada: any[] = [];
  filtro: string = '';

  constructor(private energyService: EnergyService) {}

  ngOnInit(): void {
    this.obtenerDatos();
  }

  obtenerDatos() {
    this.energyService.getEnergy().subscribe({
      next: (data) => {
        if (!data || data.length === 0) return;
        this.listaCompleta = data;
        this.listaFiltrada = [...data]; 


        setTimeout(() => {
          this.generarGraficas();
        }, 1200); 
      },
      error: (err) => console.error('Error en API:', err)
    });
  }

  calcularProduccionTotal(): string {
    const total = this.listaFiltrada.reduce((acc, item) => {
      return acc + (Number(item.hydroTwh) || 0) + (Number(item.solarTwh) || 0) + (Number(item.windTwh) || 0);
    }, 0);
    return total.toLocaleString('en-US', { minimumFractionDigits: 2 });
  }

  calcularPorcentajeRegion(): string {
    if (this.listaFiltrada.length === 0) return '0.00';
    return (this.listaFiltrada.length / 100).toFixed(2); // Ajuste temporal para mostrar algo
  }

  filtrarDatos() {
    const busqueda = this.filtro.toLowerCase().trim();
    this.listaFiltrada = this.listaCompleta.filter(item => 
      item.entity?.toLowerCase().includes(busqueda)
    );
    this.generarGraficas();
  }

  generarGraficas() {
    try {
      this.destruirGraficas();
      if (this.listaFiltrada.length > 0) {
        this.renderBarChart();
        this.renderPieChart();
        this.renderLineChart();
      }
    } catch (error) {
      console.error("Error renderizando gráficas:", error);
    }
  }

  private destruirGraficas() {
    if (this.chartBarra) this.chartBarra.destroy();
    if (this.chartTorta) this.chartTorta.destroy();
    if (this.chartTendencia) this.chartTendencia.destroy();
  }

  private renderBarChart() {
    if (!this.canvasBarra?.nativeElement) return;
    const datos = [...this.listaFiltrada].slice(0, 10);
    this.chartBarra = new Chart(this.canvasBarra.nativeElement, {
      type: 'bar',
      data: {
        labels: datos.map(item => item.entity),
        datasets: [
          { label: 'Hidro', data: datos.map(item => item.hydroTwh || 0), backgroundColor: '#3b82f6' },
          { label: 'Solar', data: datos.map(item => item.solarTwh || 0), backgroundColor: '#fbbf24' }
        ]
      },
      options: { responsive: true, maintainAspectRatio: false }
    });
  }

  private renderPieChart() {
    if (!this.canvasTorta?.nativeElement) return;
    const item = this.listaFiltrada[0];
    this.chartTorta = new Chart(this.canvasTorta.nativeElement, {
      type: 'line',
      data: {
        labels: ['Solar', 'Hidro', 'Viento'],
        datasets: [{
          data: [item.solarTwh || 0, item.hydroTwh || 0, item.windTwh || 0],
          backgroundColor: ['#fbbf24', '#3b82f6', '#10b981']
        }]
      },
      options: { responsive: true, maintainAspectRatio: false }
    });
  }

  private renderLineChart() {
    if (!this.canvasTendencia?.nativeElement) return;

    const datos = [...this.listaCompleta]
      .filter(item => item.dataYear)
      .sort((a, b) => a.dataYear - b.dataYear);

    this.chartTendencia = new Chart(this.canvasTendencia.nativeElement, {
      type: 'line',
      data: {
        labels: datos.map(item => item.dataYear),
        datasets: [{
          label: 'Producción Solar Global',
          data: datos.map(item => item.solarTwh || 0),
          borderColor: '#fbbf24',
          fill: true
        }]
      },
      options: { responsive: true, maintainAspectRatio: false }
    });
  }
}