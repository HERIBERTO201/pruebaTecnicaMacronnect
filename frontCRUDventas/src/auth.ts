import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginModel } from './models/loginModel';

@Injectable({
  providedIn: 'root'
})

export class Auth {
    private apiUrl = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient) {}

  login(data: LoginModel): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }

  guardarSesion(usuario: string) {
    localStorage.setItem('usuario', usuario);
  }

  getUsuario() {
    return localStorage.getItem('usuario');
  }

  cerrarSesion() {
    localStorage.removeItem('usuario');
  }

  estaLogueado(): boolean {
    return localStorage.getItem('usuario') !== null;
  }
}
