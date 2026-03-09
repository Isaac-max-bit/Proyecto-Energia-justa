import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Region } from '../../models/region.model';

@Injectable({
  providedIn: 'root'
})
export class RegionService {
    private apiUrl = "http://localhost:8081/api/region";

    constructor(private http: HttpClient) { }

    getRegions(): Observable<Region[]> {
        return this.http.get<Region[]>(this.apiUrl);
    }
    createRegion(region: Region): Observable<Region> {
        return this.http.post<Region>(this.apiUrl, region);
    }
}
