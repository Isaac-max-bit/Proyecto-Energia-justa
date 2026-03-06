import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router'; 
import { UserService } from '../../../services/user.service/user.service'; 
import { User } from '../../../models/user.model'; 

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule, 
    ReactiveFormsModule, 
    RouterModule 
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  userForm: FormGroup;

  constructor(
    private userSer: UserService, 
    private router: Router         
  ) {
    // Definimos el formulario con los campos necesarios
    this.userForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      role: new FormControl('usuario', [Validators.required])
    });
  }

  ngOnInit(): void {}

  // Método para procesar el registro
  onRegister(): void {
    if (this.userForm.valid) {
      const newUser: User = this.userForm.value;

      this.userSer.addUser(newUser);
      
      console.log('Usuario registrado con éxito:', newUser);
      

      this.router.navigate(['/login']);
    } else {
      this.userForm.markAllAsTouched();
    }
  }
}