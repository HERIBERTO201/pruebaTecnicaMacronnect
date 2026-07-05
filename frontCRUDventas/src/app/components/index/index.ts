import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Auth } from '../../../auth';

@Component({
  selector: 'app-index',
  imports: [RouterModule],
  templateUrl: './index.html',
  styleUrl: './index.css',
})
export class Index {

  constructor(
    private auth:Auth,
    private router:Router
  ){}


  cerrarSesion(){

    this.auth.cerrarSesion();

    this.router.navigate(['']);

  }
}
