import { Routes } from '@angular/router';
import { LoginComponent } from './view/login/login.component';
import { UserComponent } from './view/user/user.component/user.component';
import { CountryComponent } from './view/country/country.component/country.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Al abrir, va al login
  { path: 'login', component: LoginComponent },        // Pantalla de diseño verde
  { path: 'users', component: UserComponent },
  { path: 'register', component: UserComponent }       // Pantalla de tarjetas
];