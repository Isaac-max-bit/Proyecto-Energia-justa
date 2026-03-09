import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../../models/company.model';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
    private apiUrl = "http://localhost:8081/api/company";

    constructor(private http: HttpClient) { }

    getCompanies(): Observable<Company[]> {
        return this.http.get<Company[]>(this.apiUrl);
    }
    createCompany(company: Company): Observable<Company> {
        return this.http.post<Company>(this.apiUrl, company);
    }
}