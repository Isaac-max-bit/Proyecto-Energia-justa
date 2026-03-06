import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
    selector: 'app-user',
    standalone: true,
    
    imports: [CommonModule, ReactiveFormsModule, RouterLink, RouterLinkActive],
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
   
    userForm!: FormGroup;

    constructor() { }

    ngOnInit(): void {
        
        this.userForm = new FormGroup({
            username: new FormControl('', [
                Validators.required,
                Validators.minLength(3)
            ]),
            email: new FormControl('', [
                Validators.required,
                Validators.email
            ]),
            role: new FormControl('usuario', [
                Validators.required
            ])
        });
    }

    onRegister(): void {
        if (this.userForm.valid) {
            console.log('Datos del nuevo usuario:', this.userForm.value);


            alert('Usuario registrado exitosamente');
            this.userForm.reset({
                role: 'usuario'
            });
        } else {
            
        }
    }
}