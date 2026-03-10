import { Component, OnInit } from '@angular/core';
import { Country } from '../../../models/country.model';
import { CountryService } from '../../../services/country.service/country.service';
import { FormsModule } from '@angular/forms';

@Component({
    imports:[FormsModule],
    selector: 'app-country',
    templateUrl: './country.component.html',
    styleUrl: './country.component.css'
})
export class CountryComponent implements OnInit{
    countries: Country[] = [];
    newCountry: Country = new Country();

    constructor(private countryService : CountryService){}

    ngOnInit(): void {
        this.loadCountries();
        }

    loadCountries() {
        this.countryService.getCountries().subscribe(data => {
        this.countries = data;
        });
    }

    saveCountry() {
        this.countryService.createCountry(this.newCountry).subscribe(data => {
        this.loadCountries();
        this.newCountry = new Country();
        });
    }

}
