import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/user.model'; // Importa tu interfaz
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class UserService {
    // Es recomendable definir la URL en una variable
    private jsonUrl = 'pruebausuario.json'; 

    constructor (private http: HttpClient){}

    getUsers(): Observable<User[]> {
        // Ahora el servicio sabe exactamente qué tipo de datos devuelve
        return this.http.get<User[]>(this.jsonUrl);
    }
}