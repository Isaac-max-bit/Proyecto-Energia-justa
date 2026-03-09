import { Routes } from '@angular/router';
import { LoginComponent } from './view/login/login.component'; 
import { RegisterComponent } from './view/register/register.component/register.component';
import { UserComponent } from './view/user/user.component/user.component';
import { EnergyComponent } from './view/energy/energy.component/energy.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user', component: UserComponent },
  { path: 'energy', component: EnergyComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];