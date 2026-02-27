import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CountryService } from '../../../services/country.service/country.service';
import { ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-country.component',
  imports: [CommonModule],
  templateUrl: './country.component.html',
  styleUrl: './country.component.css',
})
export class CountryComponent {
    countries: any[] = [];

    constructor(private countrySer: CountryService, private changeP: ChangeDetectorRef){
        countrySer.getCountries().subscribe(data =>{
            this.countries = data;
            console.log(this.countries);
            this.changeP.detectChanges();
        });
    }
}
