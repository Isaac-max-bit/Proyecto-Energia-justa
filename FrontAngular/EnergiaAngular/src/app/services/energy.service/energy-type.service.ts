import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EnergyType } from '../../models/energy-type.model';

@Injectable({
    providedIn: 'root'
})
export class EnergyTypeService {
    private apiUrl = "http://localhost:8081/api/energy-type";

    constructor(private http: HttpClient) { }

    getEnergyTypes(): Observable<EnergyType[]> {
        return this.http.get<EnergyType[]>(this.apiUrl);
    }
    createEnergyType(energyType: EnergyType): Observable<EnergyType> {
        return this.http.post<EnergyType>(this.apiUrl, energyType);
    }
}
