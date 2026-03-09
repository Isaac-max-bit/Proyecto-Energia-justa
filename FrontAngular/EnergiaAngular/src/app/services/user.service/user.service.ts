import { Injectable } from '@angular/core';
import { User } from '../../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private users: User[] = []; 

    private apiUrl="http://localhost:8081/api/users"

    constructor(private http:HttpClient) { }

    getUsers2(): Observable<User[]>{
        return this.http.get<User[]>(this.apiUrl);
    }
    addUser(user: User): void {
        this.users.push(user);
        console.log('Usuario guardado en el servicio:', user);
    }

    getUsers(): User[] {
        return this.users;
    }
}