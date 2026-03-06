import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Region } from '../../models/region.model';

@Injectable({
    providedIn: 'root',
})
export class RegionService {
    private apiUrl = "apigenerica.api"

    constructor (private http: HttpClient){}

    getRegion(){
        return this.http.get<any[]>('region.json')
    }

    createRegion(newRegion: Region): Observable<Region> {
    return this.http.post<Region>(this.apiUrl, newRegion);
  }
}
