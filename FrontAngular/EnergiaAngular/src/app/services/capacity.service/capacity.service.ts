import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Capacity } from '../../models/capacity.model';

@Injectable({
  providedIn: 'root',
})
export class CapacityService {
  private apiUrl = "http://localhost:8080/api/energy/capacity";

  constructor(private http: HttpClient) {}

  getCapacity(): Observable<Capacity[]> {
    // Credenciales para Basic Auth
    const user = 'admin@energy.com';
    const pass = 'Admin';
    const authHeader = 'Basic ' + btoa(`${user}:${pass}`);

    const headers = new HttpHeaders({
      'Authorization': authHeader,
      'Content-Type': 'application/json'
    });

    return this.http.get<Capacity[]>(this.apiUrl, { headers });
  }
}
