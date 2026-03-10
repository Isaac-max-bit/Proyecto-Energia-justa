import { Component, OnInit } from '@angular/core';
import { EnergyService } from '../../../services/energy.service/energy.service';
import { Energy } from '../../../models/energy.model';
import { CommonModule } from '@angular/common'; // Importante para el *ngFor

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule], // Permite usar tablas y listas
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  listaEnergia: Energy[] = [];
totalProduction: number = 0;

  constructor(private energyService: EnergyService) {}

  ngOnInit(): void {
    this.energyService.getEnergy().subscribe({
      next: (data) => {
        this.listaEnergia = data;
        console.log("Datos cargados:", data);
      },
      error: (err) => console.error("Error al conectar con Java:", err)
    });
  }
}
