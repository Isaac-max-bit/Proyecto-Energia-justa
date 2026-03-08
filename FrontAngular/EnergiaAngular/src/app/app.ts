import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from "./view/login/login.component";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule], // Importante para que router-outlet funcione
  templateUrl: './app.html', // CORRECCIÓN: Quitamos '.component' del nombre
  styleUrl: './app.css',
})
export class AppComponent { // Asegúrate de que se llame AppComponent, no 'App'
  title = 'EnergiaAngular';
}

