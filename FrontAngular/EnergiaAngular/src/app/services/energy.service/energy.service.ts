import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnergyData } from '../../models/energy.model';

@Injectable({
  providedIn: 'root',
})
export class EnergyService {
  private baseUrl = "http://localhost:8080/api/energy";

  constructor(private http: HttpClient) {}

  // Helper para las credenciales (evita repetir código)
  private getHeaders(): HttpHeaders {
    const authHeader = 'Basic ' + btoa('admin@energy.com:Admin');
    return new HttpHeaders({
      'Authorization': authHeader,
      'Content-Type': 'application/json'
    });
  }

  // Producción total por tipo y región (Tabla)
  getProduccion(year: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/produccion?year=${year}`, { headers: this.getHeaders() });
  }

  //TODO
  getProduccion(){

  }

  getTopEeolica(){

  }

  getParticipacion(){

  }
}