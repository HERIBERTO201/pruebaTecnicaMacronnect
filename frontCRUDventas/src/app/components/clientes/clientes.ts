import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

import { ClientesService } from './clientes-service';
import { ClientesModel } from '../../../models/clientes-model';

@Component({
  selector: 'app-clientes',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './clientes.html',
  styleUrl: './clientes.css',
})
export class Clientes {
  clientes: ClientesModel[] = [];

  formCliente!: FormGroup;

  editando = false;
  clienteIdEditando: number | null = null;

  constructor(
    private clientesService: ClientesService,
    private fb: FormBuilder,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.formCliente = this.fb.group({
      nombre: ['', Validators.required],
      email: ['', Validators.required],
      telefono: [''],
      direccion: ['']
    });

    this.cargarClientes();
  }

  cargarClientes() {

    this.clientesService.obtenerTodos().subscribe({

      next: (data) => {
        this.clientes = [...data];
        this.cd.detectChanges();
      },

      error: (err) => console.error(err)

    });

  }

  guardar() {

    const cliente: ClientesModel = this.formCliente.value;

    if (this.editando && this.clienteIdEditando != null) {

      this.clientesService.actualizar(this.clienteIdEditando, cliente).subscribe({

        next: () => {
          this.cargarClientes();
          this.cancelarEdicion();
        },

        error: (err) => console.error(err)

      });

    } else {

      this.clientesService.guardar(cliente).subscribe({

        next: () => {
          this.cargarClientes();
          this.formCliente.reset();
        },

        error: (err) => console.error(err)

      });

    }

  }

  editar(cliente: ClientesModel) {

    this.editando = true;
    this.clienteIdEditando = cliente.id!;

    this.formCliente.patchValue({
      nombre: cliente.nombre,
      email: cliente.email,
      telefono: cliente.telefono,
      direccion: cliente.direccion
    });

  }

  eliminar(id: number) {

    this.clientesService.eliminar(id).subscribe({

      next: () => this.cargarClientes(),
      error: (err) => console.error(err)

    });

  }

  cancelarEdicion() {

    this.editando = false;
    this.clienteIdEditando = null;
    this.formCliente.reset();

  }
}
