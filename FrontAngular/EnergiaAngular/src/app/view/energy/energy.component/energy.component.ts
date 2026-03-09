import { Component,OnInit } from '@angular/core';
import { Energy } from '../../../models/energy.model';
import { EnergyService } from '../../../services/energy.service/energy.service';

@Component({
  selector: 'app-energy.component',
 // imports: [],
  templateUrl: './energy.component.html',
  styleUrl: './energy.component.css',
})
export class EnergyComponent implements OnInit{
   energy:Energy[]=[];

  constructor(private energyService:EnergyService){}

  ngOnInit(): void {
    this.loadEnergy();
  }
  
  loadEnergy() {
    this.energyService.getEnergy().subscribe(data => {
      this.energy = data;
    });
  }
}
