import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClientesModel } from '../../../models/clientes-model';

@Injectable({
  providedIn: 'root'
})

export class ClientesService {
    private api = 'http://localhost:8080/clientes';

  constructor(private http: HttpClient) {}

  obtenerTodos(): Observable<ClientesModel[]> {
    return this.http.get<ClientesModel[]>(this.api);
  }

  guardar(cliente: ClientesModel): Observable<ClientesModel> {
    return this.http.post<ClientesModel>(this.api, cliente);
  }

  actualizar(id: number, cliente: ClientesModel): Observable<ClientesModel> {
    return this.http.put<ClientesModel>(`${this.api}/${id}`, cliente);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
