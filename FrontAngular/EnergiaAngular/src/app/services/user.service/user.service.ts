import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root',
})
export class UserService {
    constructor (private http: HttpClient){}

    getUsers(){
        return this.http.get<any[]>('pruebausuario.json');
    }

    getUserById(id : number){
        return this.http.get(`http://localhost:8081/api/users/${id}`);
    }
}
