import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Medicine,Sickness } from '../_models';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Medicine[]>(`${environment.apiUrl}/api/medicine`);
  }

  insert(medicine: Medicine) {
    return this.http.post(`${environment.apiUrl}/api/medicine`, medicine);
  }

  search(query : String) {
    return this.http.get<Medicine[]>(`${environment.apiUrl}/api/findmedicine/`+query);
  }

  findsickness(query : String) {
    return this.http.get<Sickness[]>(`${environment.apiUrl}/api/findsickness/`+query);
  }
}
