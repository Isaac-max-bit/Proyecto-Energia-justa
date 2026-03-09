import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Country } from '../../../models/country.model';
import { CountryService } from '../../../services/country.service/country.service';
import { Company } from '../../../models/company.model';
import { CompanyService } from '../../../services/company.service/company.service';
import { FormsModule } from '@angular/forms';


@Component({
    selector: 'app-company.component',
    imports:[FormsModule],
    templateUrl: './company.component.html',
    styleUrl: './company.component.css',
})

    export class CompanyComponent implements OnInit{
        countries: Country[] = [];
        companies: Company[] = [];
        newCompany: Company = new Company();

    constructor(private countryService: CountryService, private companyService: CompanyService) {}

    ngOnInit(): void {
        this.loadCountries();
        this.loadCompanies();
    }

    loadCountries() {
        this.countryService.getCountries().subscribe(data => {
        this.countries = data;
        });
    }

    loadCompanies() {
        this.companyService.getCompanies().subscribe(data => {
        this.companies = data;
        });
    }

    saveCompany() {
        this.companyService.createCompany(this.newCompany).subscribe(data => {
        this.loadCompanies();
        this.newCompany = new Company();
        });
    }
}
