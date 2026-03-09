import { Routes } from '@angular/router';
import { LoginComponent } from './view/login/login.component'; 
import { RegisterComponent } from './view/register/register.component/register.component';
import { CountryComponent } from './view/country/country.component/country.component';
import { RegionComponent } from './view/region/region.component/region.component';
import { PowerPlantComponent } from './view/power-plant/power-plant.component/power-plant.component';
import { CompanyComponent } from './view/company/company.component/company.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    {path: 'country', component: CountryComponent},
    {path: 'region', component: RegionComponent},
    {path: 'company', component: CompanyComponent},
    {path: 'power-plant', component: PowerPlantComponent}
];