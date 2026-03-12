import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnergyData } from '../../models/energy.model';

@Injectable({
  providedIn: 'root',
})
export class EnergyService {
  private baseUrl = "http://localhost:8081/api/energy";

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

  //  Porcentaje de energía renovable (Gráfico de Torta)
  getParticipacion(year: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/porcentaje?year=${year}`, { headers: this.getHeaders() });
  }

  //  Tendencia capacidad solar (Gráfico de Líneas)
  getTendenciaSolar(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/capacity`, { headers: this.getHeaders() });
  }

  //  Top 10 países eólica (Gráfico de Barras)
  getTopEolica(year: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/top-eolica?year=${year}`, { headers: this.getHeaders() });
  }

  // Lista global de todas las fuentes
  getEnergy(): Observable<EnergyData[]> {
    return this.http.get<EnergyData[]>(`${this.baseUrl}/all`, { headers: this.getHeaders() });
  }
}