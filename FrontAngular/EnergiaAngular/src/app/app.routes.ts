import { Routes } from '@angular/router';
import { LoginComponent } from './view/login/login.component';
import { UserComponent } from './view/user/user.component/user.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: UserComponent } // El componente que acabamos de crear
];