import { Component, OnInit } from '@angular/core';
import { EnergyService } from '../../../services/energy.service/energy.service';
import { Energy } from '../../../models/energy.model';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { Chart } from 'chart.js/auto'; 
import { ViewChild, ElementRef } from '@angular/core'; 


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  // REFERENCIA AL ELEMENTO CANVAS DEL HTML
  @ViewChild('miGrafica') canvas!: ElementRef;
  chart: any;

  listaCompleta: Energy[] = [];
  listaFiltrada: Energy[] = [];
  filtro: string = '';

  constructor(private energyService: EnergyService) {}

  ngOnInit(): void {
    this.obtenerDatos();
  }

  obtenerDatos() {
    this.energyService.getEnergy().subscribe({
      next: (data) => {
        this.listaCompleta = data;
        this.listaFiltrada = data;
        //  GRÁFICA PARA DESPUÉS DE RECIBIR DATOS
        this.generarGrafica();
      }
    });
  }

  filtrarDatos() {
    const busqueda = this.filtro.toLowerCase();
    this.listaFiltrada = this.listaCompleta.filter(item => 
      item.entity.toLowerCase().includes(busqueda)
    );
    //  ACTUALIZAMOS LA GRÁFICA AL FILTRAR
    this.generarGrafica();
  }

  generarGrafica() {
    // Si ya existe una gráfica, la destruimos para crear la nueva
    if (this.chart) {
      this.chart.destroy();
    }

    // Tomamos los últimos 10 registros para que la gráfica 
    const datosRecientes = this.listaFiltrada.slice(0, 10);
    const etiquetas = datosRecientes.map(item => `${item.entity} (${item.year})`);
    const valoresSolar = datosRecientes.map(item => item.solarProduction || 0);
    const valoresHidro = datosRecientes.map(item => item.hydroProduction || 0);

    this.chart = new Chart(this.canvas.nativeElement, {
      type: 'line', // Tipo de gráfica: barras
      data: {
        labels: etiquetas,
        datasets: [
          {
            label: 'Energía Solar (TWh)',
            data: valoresSolar,
            backgroundColor: '#f1c40f'
          },
          {
            label: 'Energía Hidroeléctrica (TWh)',
            data: valoresHidro,
            backgroundColor: '#3498db'
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false
      }
    });
  }

  calcularPromedio(campo: keyof Energy): string {
    if (this.listaFiltrada.length === 0) return '0';
    const suma = this.listaFiltrada.reduce((acc, item) => acc + (Number(item[campo]) || 0), 0);
    return (suma / this.listaFiltrada.length).toFixed(2);
  }
}