import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Energy } from '../../models/energy.model';

@Injectable({
  providedIn: 'root',
})
export class EnergyService {
  private apiUrl="http://localhost:8081/api/energy/all";

  constructor(private http: HttpClient){}

  getEnergy(): Observable<Energy[]> {
    return this.http.get<Energy[]>(this.apiUrl);
  }
  
}