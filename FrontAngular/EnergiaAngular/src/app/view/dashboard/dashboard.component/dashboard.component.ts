import { Component, OnInit } from '@angular/core';
import { EnergyService } from '../../../services/energy.service/energy.service';
import { Energy } from '../../../models/energy.model';
import { CommonModule } from '@angular/common'; // Importante si usas standalone

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  // Aquí se guardarán los datos de África/Afghanistan
  listaEnergia: Energy[] = [];

  constructor(private energyService: EnergyService) {}

  ngOnInit(): void {
    this.obtenerDatos();
  }

  obtenerDatos(): void {
    this.energyService.getEnergy().subscribe({
      next: (data) => {
        this.listaEnergia = data;
        console.log('¡Datos cargados con éxito!', data);
      },
      error: (err) => {
        console.error('Error al conectar con el servidor:', err);
      }
    });
  }
}
