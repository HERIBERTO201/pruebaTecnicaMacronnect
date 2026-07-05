import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { VentasService } from './ventas-service';
import { VentaModel } from '../../../models/venta-model';
import { DetalleVentaModel } from '../../../models/detalle-venta-model';
import { ClientesService } from '../clientes/clientes-service';
import { ProductosService } from '../productos/productos-service';

@Component({
  selector: 'app-ventas',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ventas.html',
  styleUrl: './ventas.css',
})
export class Ventas implements OnInit {
formVenta!: FormGroup;

  clientes: any[] = [];
  productos: any[] = [];
  ventas: any[] = [];
  carrito: DetalleVentaModel[] = [];

  total = 0;

  constructor(
    private fb: FormBuilder,
    private ventasService: VentasService,
    private clientesService: ClientesService,
    private productosService: ProductosService
  ) {}

  ngOnInit(): void {

    this.formVenta = this.fb.group({
      clienteID: ['', Validators.required],
      productoID: [''],
      cantidad: [1]
    });

    this.clientesService.obtenerTodos().subscribe(res => this.clientes = res);
    this.productosService.obtenerTodos().subscribe(res => this.productos = res);
    this.cargarVentas();
  }

  agregarProducto() {

  const productoID = this.formVenta.value.productoID;

  const producto = this.productos.find(p => p.id == productoID);

  const cantidad = this.formVenta.value.cantidad;

  if (!producto) {
    console.warn('Producto no seleccionado');
    return;
  }

  const detalle: DetalleVentaModel = {
    productoID: producto.id!,
    cantidad: cantidad,
    precioUnitario: producto.precio,
    subtotal: producto.precio * cantidad
  };

  this.carrito.push(detalle);

  this.calcularTotal();
}

  calcularTotal() {

    this.total = this.carrito.reduce((sum, item) => sum + item.subtotal, 0);

  }

  eliminarItem(index: number) {

    this.carrito.splice(index, 1);

    this.calcularTotal();

  }

  guardarVenta() {

    const venta: VentaModel = {

      clienteId: this.formVenta.value.clienteID,
      fecha: new Date().toISOString(),
      total: this.total,
      folio: 'V-' + Date.now(),
      estado: 'ACTIVO',
      detalles: this.carrito

    };

    this.ventasService.guardar(venta).subscribe({

      next: () => {

        this.carrito = [];
        this.total = 0;
        this.formVenta.reset();
        this.cargarVentas();
      },

      error: err => console.error(err)

    });

  }

  cargarVentas() {

  this.ventasService.obtenerTodas().subscribe({

    next: (data) => {
      this.ventas = data;
    },

    error: (err) => console.error(err)

  });

}
}
