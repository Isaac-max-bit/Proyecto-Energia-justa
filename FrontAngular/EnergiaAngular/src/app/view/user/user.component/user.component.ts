import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service/user.service';
import { CommonModule } from '@angular/common';
import { User } from '../../../models/user.model';
// 1. Importamos las herramientas de formularios reactivos
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-user',
    standalone: true,
    // 2. IMPORTANTE: Agregamos ReactiveFormsModule aquí para quitar el error del HTML
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './user.component.html',
    styleUrl: './user.component.css',
})
export class UserComponent implements OnInit {
    users: User[] = [];
    userForm: FormGroup; // 3. Definimos el grupo del formulario

    constructor(private userSer: UserService) {
        // 4. Creamos el formulario con sus validaciones
        this.userForm = new FormGroup({
            username: new FormControl('', [Validators.required, Validators.minLength(3)]),
            email: new FormControl('', [Validators.required, Validators.email]),
            role: new FormControl('usuario', Validators.required)
        });
    }

    ngOnInit(): void {
        // Carga inicial de datos desde el JSON
        this.userSer.getUsers().subscribe({
            next: (data) => {
                this.users = data;
                console.log('Usuarios cargados:', this.users);
            },
            error: (err) => console.error('Error al cargar:', err),
        });
    }

    // 5. Función para agregar usuarios a la lista (Simulación Front-end)
    addUser(): void {
        if (this.userForm.valid) {
            const newUser: User = {
                id: this.users.length + 1,
                username: this.userForm.value.username,
                email: this.userForm.value.email,
                role: this.userForm.value.role,
                password: '123', // Password genérico para pruebas
                createdAt: new Date().toISOString().split('T')[0],
                updatedAt: new Date().toISOString().split('T')[0]
            };

            // Actualizamos el array local para que aparezca la nueva tarjeta
            this.users = [...this.users, newUser];

            // Limpiamos el formulario
            this.userForm.reset({ role: 'usuario' });
        }
    }

    // 6. Función para eliminar usuarios de la vista
    deleteUser(id: number): void {
        if (confirm('¿Estás seguro de eliminar este usuario?')) {
            this.users = this.users.filter(u => u.id !== id);
        }
    }
}