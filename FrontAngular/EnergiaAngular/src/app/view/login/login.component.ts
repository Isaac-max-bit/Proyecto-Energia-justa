import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
    selector: 'app-login',
    standalone: true,

    imports: [CommonModule, ReactiveFormsModule, RouterLink],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    // Definimos el formulario reactivo
    loginForm!: FormGroup;

    constructor(private router: Router) { }

    ngOnInit(): void {
        // Inicializamos los campos con sus validaciones
        this.loginForm = new FormGroup({
            email: new FormControl('', [
                Validators.required,
                Validators.email
            ]),
            password: new FormControl('', [
                Validators.required,
                Validators.minLength(6)
            ])
        });
    }

    // Método para procesar el ingreso
    onSubmit(): void {
        if (this.loginForm.valid) {
            console.log('Datos de login:', this.loginForm.value);
            // Aquí iría la lógica para validar con tu backend
            alert('Intento de login con: ' + this.loginForm.value.email);
        } else {
            // Marcamos los campos como tocados para mostrar errores si fuera necesario
            this.loginForm.markAllAsTouched();
        }
    }

    // Método opcional si prefieres navegar por código en lugar de usar routerLink
    goToRegister(): void {
        this.router.navigate(['/register']);
    }
}