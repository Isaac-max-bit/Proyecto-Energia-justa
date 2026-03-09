import { Component, OnInit } from '@angular/core';
import { Country } from '../../../models/country.model';
import { CountryService } from '../../../services/country.service/country.service';
import { Region } from '../../../models/region.model';
import { RegionService } from '../../../services/region.service/region.service';
import { FormsModule } from '@angular/forms';

@Component({
    imports:[FormsModule],
    selector: 'app-region',
    templateUrl: './region.component.html',
    styleUrl: './region.component.css'
    })

    export class RegionComponent implements OnInit{
        countries: Country[] = [];
        regions: Region[] = [];
        newRegion: Region = new Region();

    constructor(private countryService: CountryService, private regionService: RegionService) {}

    ngOnInit(): void {
        this.loadCountries();
        this.loadRegions();
    }

    loadCountries() {
        this.countryService.getCountries().subscribe(data => {
        this.countries = data;
        });
    }

    loadRegions() {
        this.regionService.getRegions().subscribe(data => {
        this.regions = data;
        });
    }

    saveRegion() {
        this.regionService.createRegion(this.newRegion).subscribe(data => {
        this.loadRegions();
        this.newRegion = new Region();
        });
    }

}