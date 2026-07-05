import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

import { ProductosService } from './productos-service';
import { ProductoModel } from '../../../models/producto-model';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './productos.html',
  styleUrl: './productos.css'
})
export class Productos implements OnInit {

  productos: ProductoModel[] = [];

  formProducto!: FormGroup;

  editando = false;
  productoIdEditando: number | null = null;

  constructor(
    private productoService: ProductosService,
    private fb: FormBuilder,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.formProducto = this.fb.group({
      codigo: ['', Validators.required],
      nombre: ['', Validators.required],
      descripcion: [''],
      precio: [0, Validators.required],
      stock: [0, Validators.required]
    });

    this.cargarProductos();
  }

  cargarProductos() {

    this.productoService.obtenerTodos().subscribe({

      next: (data) => {
        this.productos = data;
        this.cd.detectChanges();
      },

      error: (err) => console.error(err)

    });

  }

  guardar() {

    const producto: ProductoModel = this.formProducto.value;

    if (this.editando && this.productoIdEditando != null) {

      // 🔥 UPDATE
      this.productoService.actualizar(this.productoIdEditando, producto).subscribe({

        next: () => {

          this.cargarProductos();

          this.cancelarEdicion();

        },

        error: (err) => console.error(err)

      });

    } else {

      // 🔥 CREATE
      this.productoService.guardar(producto).subscribe({

        next: () => {

          this.cargarProductos();

          this.formProducto.reset();

        },

        error: (err) => console.error(err)

      });

    }

  }

  editar(producto: ProductoModel) {

    this.editando = true;
    this.productoIdEditando = producto.id!;

    this.formProducto.patchValue({
      codigo: producto.codigo,
      nombre: producto.nombre,
      descripcion: producto.descripcion,
      precio: producto.precio,
      stock: producto.stock
    });

  }

  eliminar(id: number) {

    this.productoService.eliminar(id).subscribe({

      next: () => this.cargarProductos(),

      error: (err) => console.error(err)

    });

  }

  cancelarEdicion() {

    this.editando = false;
    this.productoIdEditando = null;
    this.formProducto.reset();

  }
}