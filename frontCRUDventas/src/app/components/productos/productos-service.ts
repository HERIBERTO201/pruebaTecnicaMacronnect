import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ProductoModel } from '../../../models/producto-model';

@Injectable({
  providedIn: 'root'
})

export class ProductosService {
    private api = 'http://localhost:8080/productos';

  constructor(private http: HttpClient) { }

  obtenerTodos(): Observable<ProductoModel[]> {
    return this.http.get<ProductoModel[]>(this.api);
  }

  obtenerPorNombre(nombre: string): Observable<ProductoModel[]> {
    return this.http.get<ProductoModel[]>(`${this.api}/nombre/${nombre}`);
  }

  obtenerPorCodigo(codigo: string): Observable<ProductoModel> {
    return this.http.get<ProductoModel>(`${this.api}/codigo/${codigo}`);
  }

  guardar(producto: ProductoModel): Observable<ProductoModel> {
    return this.http.post<ProductoModel>(this.api, producto);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  actualizar(id: number, producto: ProductoModel): Observable<ProductoModel> {
    return this.http.put<ProductoModel>(`${this.api}/${id}`, producto);
  }
}
