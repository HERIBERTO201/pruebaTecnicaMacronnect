import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Auth } from '../../../auth';
import { LoginModel } from '../../../models/loginModel'

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})

export class Login {
  
  usuario = '';
  password = '';
  error = '';

  constructor(private auth: Auth) {}

  login() {

    const data: LoginModel = {
      usuario: this.usuario,
      password: this.password
    };

    this.auth.login(data).subscribe({
      next: (res) => {

        if (res.success) {
          this.auth.guardarSesion(res.usuario);
          this.error = '';
          alert('Login exitoso');

          // opcional: redirigir
          // window.location.href = '/ventas';
        } else {
          this.error = res.message;
        }
      },
      error: () => {
        this.error = 'Error en el servidor';
      }
    });
  }
}
