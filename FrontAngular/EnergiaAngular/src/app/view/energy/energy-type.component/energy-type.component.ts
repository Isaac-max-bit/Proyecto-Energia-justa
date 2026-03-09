import { Component, OnInit } from '@angular/core';
import { EnergyType } from '../../../models/energy-type.model';
import { EnergyTypeService } from '../../../services/energy.service/energy-type.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-energy-type.component',
  imports: [FormsModule],
  templateUrl: './energy-type.component.html',
  styleUrl: './energy-type.component.css',
})
export class EnergyTypeComponent implements OnInit{
    EnergyTypes: EnergyType[] = [];
    newEnergyType: EnergyType = new EnergyType();

    constructor(private energyTypeService : EnergyTypeService){}

        ngOnInit(): void {
        this.loadEnergyTypes();
        }

    loadEnergyTypes() {
        this.energyTypeService.getEnergyTypes().subscribe(data => {
        this.EnergyTypes = data;
        });
    }

    saveEnergyType() {
        this.energyTypeService.createEnergyType(this.newEnergyType).subscribe(data => {
        this.loadEnergyTypes();
        this.newEnergyType = new EnergyType();
        });
    }
}
