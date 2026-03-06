import { Routes } from '@angular/router';
import { LoginComponent } from './view/login/login.component';
import { UserComponent } from './view/user/user.component/user.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Al abrir, va al login
  { path: 'login', component: LoginComponent },        // Pantalla de diseño verde
  { path: 'usuarios', component: UserComponent }       // Pantalla de tarjetas
];