import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http'; // 1. Importamos HttpClient
import { AuthService } from '../../services/authenticator.service/auth.service'; // 2. Importa tu servicio

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, RouterModule],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;

    // 3. Inyectamos AuthService y HttpClient
    constructor(
        private router: Router, 
        private authService: AuthService,
        private http: HttpClient 
    ) {
        this.loginForm = new FormGroup({
            email: new FormControl('', [Validators.required]), 
            password: new FormControl('', [Validators.required, Validators.minLength(1)])
        });
    }

    ngOnInit(): void { }

    onLogin(): void {
        if (this.loginForm.valid) {
            const { email, password } = this.loginForm.value;

            // 1. Guardamos las credenciales
            this.authService.saveCredentials(email, password);

            // 2. Intentamos la conexión
            this.http.get('http://localhost:8081/api/energy/', { observe: 'response' })
                .subscribe({
                    next: (response) => {
                        // Si entra aquí, es que NO hubo error 401. ¡Éxito!
                        this.validarYRedirigir();
                    },
                    error: (err) => {
                        // Si el error es 404, ¡también significa que las credenciales son correctas!
                        // (porque el 401 tiene prioridad en Spring Security)
                        if (err.status === 404) {
                            this.validarYRedirigir();
                        } else {
                            console.error('Error real de auth:', err);
                            alert('Usuario o contraseña incorrectos');
                            this.authService.logout();
                        }
                    }
                });
        }
        
    }
    private validarYRedirigir() {
        if (this.authService.isAdmin()) {
            console.log('Login exitoso. Redirigiendo a Home...');
            this.router.navigate(['/home']); 
        } else {
            alert('Acceso denegado: No eres administrador');
            this.authService.logout();
        }
    }
}