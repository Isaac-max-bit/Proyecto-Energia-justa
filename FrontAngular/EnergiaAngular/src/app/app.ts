import { Component } from '@angular/core';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './app.html', 
  styleUrls: ['./app.css']
})
export class AppComponent {
  showNavbar: boolean = false;

  constructor(private router: Router) {
    // Escucha cambios de ruta para ocultar el Navbar en Login/Register
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: any) => {
      const hideRoutes = ['/login', '/register', '/'];
      // Verifica si la ruta actual está en la lista de rutas a ocultar
      this.showNavbar = !hideRoutes.includes(event.urlAfterRedirects);
    });
  }
}