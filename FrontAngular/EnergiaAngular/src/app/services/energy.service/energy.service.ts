import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Energy } from '../../models/energy.model';

@Injectable({
  providedIn: 'root',
})
export class EnergyService {
  // URL exacta que probamos en el navegador
  private apiUrl = "http://localhost:8080/api/energy/all";
  private prodUrl= "http://localhost:8080/api/energy/produccion";
  private eolicaUrl= "http://localhost:8080/api/energy/top-eolica";
  private partUrl= "http://localhost:8080/api/energy/global-participacion";

  constructor(private http: HttpClient) {}

  getEnergy(): Observable<Energy[]> {
    // Credenciales para Basic Auth
    const user = 'admin@energy.com';
    const pass = 'Admin';
    const authHeader = 'Basic ' + btoa(`${user}:${pass}`);

    const headers = new HttpHeaders({
      'Authorization': authHeader,
      'Content-Type': 'application/json'
    });

    return this.http.get<Energy[]>(this.apiUrl, { headers });
  }

  //TODO
  getProduccion(){
    const user = 'admin@energy.com';
    const pass = 'Admin';
    const authHeader = 'Basic ' + btoa(`${user}:${pass}`);

    const headers = new HttpHeaders({
      'Authorization': authHeader,
      'Content-Type': 'application/json'
    });

    return this.http.get<Energy[]>(this.prodUrl, { headers });
  }

  getTopEeolica(){
        const user = 'admin@energy.com';
    const pass = 'Admin';
    const authHeader = 'Basic ' + btoa(`${user}:${pass}`);

    const headers = new HttpHeaders({
      'Authorization': authHeader,
      'Content-Type': 'application/json'
    });

    return this.http.get<Energy[]>(this.eolicaUrl, { headers });

  }

  getParticipacion(){

        const user = 'admin@energy.com';
    const pass = 'Admin';
    const authHeader = 'Basic ' + btoa(`${user}:${pass}`);

    const headers = new HttpHeaders({
      'Authorization': authHeader,
      'Content-Type': 'application/json'
    });

    return this.http.get<Energy[]>(this.partUrl, { headers });

  }
}