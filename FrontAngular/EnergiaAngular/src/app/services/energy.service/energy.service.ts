import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Energy } from '../../models/energy.model';

@Injectable({
  providedIn: 'root',
})
export class EnergyService {
  private apiUrl = "http://localhost:8080/api/energy";

  constructor(private http: HttpClient) {}

  getEnergy(): Observable<Energy[]> {
    // 1. Preparamos las credenciales que me diste
    const user = 'admin@energy.com';
    const pass = 'Admin';
    
    
// Prueba escribirlo manualmente para descartar caracteres invisibles
const authHeader = 'Basic ' + btoa('admin@energy.com:Admin');

    // 3. Creamos las cabeceras de la petición
    const headers = new HttpHeaders({
      'Authorization': authHeader
    });

    // 4. Enviamos la petición con la llave incluida
    return this.http.get<Energy[]>(this.apiUrl, { headers });
  }
}