import { Routes } from '@angular/router';
import { CountryComponent } from './view/country/country.component/country.component';
import { UserComponent } from './view/user/user.component/user.component';

export const routes: Routes = [
    {path: 'countries', component: CountryComponent},
    {path: 'users', component: UserComponent},
    //{path: 'login', component: LoginComponent} Más adelante
];
