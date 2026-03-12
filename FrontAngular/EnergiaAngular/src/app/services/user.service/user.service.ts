import { Injectable } from '@angular/core';
import { User } from '../../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private users: User[] = []; 

    private apiUrl="http://localhost:8080/api/users/register"

    constructor(private http:HttpClient) { }
    
    getUsers(): Observable<User[]>{
        return this.http.get<User[]>(this.apiUrl);
    }

    addUser(user: User): Observable<User> {
        return this.http.post<User>(this.apiUrl, user);
    }
    
    
    /* getUsers(): User[] {
        return this.users;
    } */
    /* addUser(user: User): void {
        this.users.push(user);
        console.log('Usuario guardado en el servicio:', user);
    } */
}