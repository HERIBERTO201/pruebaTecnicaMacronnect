import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VentaModel } from '../../../models/venta-model'; 

@Injectable({
  providedIn: 'root'
})

export class VentasService {
    private api = 'http://localhost:8080/ventas';

  constructor(private http: HttpClient) {}

  obtenerTodas(): Observable<VentaModel[]> {
    return this.http.get<VentaModel[]>(this.api);
  }

  guardar(venta: VentaModel): Observable<VentaModel> {
    return this.http.post<VentaModel>(this.api+"/registrar", venta);
  }

  obtenerPorFolio(folio: string): Observable<VentaModel> {
    return this.http.get<VentaModel>(`${this.api}/folio/${folio}`);
  }
}
