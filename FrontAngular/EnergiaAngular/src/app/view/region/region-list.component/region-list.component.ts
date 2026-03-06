import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RegionService } from '../../../services/region.service/region.service';
import { ChangeDetectorRef } from '@angular/core';

interface GroupedRegions {
  [countryName: string]: any[]; 
}

@Component({
  selector: 'app-region-list.component',
  imports: [CommonModule],
  templateUrl: './region-list.component.html',
  styleUrl: './region-list.component.css',
})
export class RegionListComponent {
    regions: any[] = [];
    groupedRegions: GroupedRegions = {};

    constructor(private regionSer : RegionService, private changeP: ChangeDetectorRef){
        this.regionSer.getRegions().subscribe(data => {
        this.groupedRegions = data.reduce((acc, region) => {
            const countryName = region.country.name; // O el campo que tenga el nombre
            if (!acc[countryName]) {
            acc[countryName] = [];
            }
            acc[countryName].push(region);
            return acc;
        }, {});
        });
    }
}
