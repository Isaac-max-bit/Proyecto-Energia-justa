import { Routes } from '@angular/router';
import { LoginComponent } from './view/login/login.component'; 
import { RegisterComponent } from './view/register/register.component/register.component';
import { CountryComponent } from './view/country/country.component/country.component';
import { RegionComponent } from './view/region/region.component/region.component';
import { PowerPlantComponent } from './view/power-plant/power-plant.component/power-plant.component';
import { CompanyComponent } from './view/company/company.component/company.component';
import { EnergyTypeComponent } from './view/energy/energy-type.component/energy-type.component';
import { UserComponent } from './view/user/user.component/user.component';
import { EnergyComponent } from './view/energy/energy.component/energy.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'user', component: UserComponent },
    { path: 'energy', component: EnergyComponent },
    {path: 'country', component: CountryComponent},
    {path: 'region', component: RegionComponent},
    {path: 'company', component: CompanyComponent},
    {path: 'energy-type', component: EnergyTypeComponent},
    {path: 'power-plant', component: PowerPlantComponent}
];