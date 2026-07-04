/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
/**
 *
 * @author herio
 */

@Entity
@Table(name = "detalle_venta")
public class detalleVentaEntity {
    
    

    @NotNull
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private ventaEntity venta;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private productoEntity producto;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Min(1)
    private int cantidad;

    @DecimalMin(value = "0.0")
    private double precioUnitario;

    @DecimalMin(value = "0.0")
    private double subtotal;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getPrecioUnitario()
    {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(double precioUnitario)
    {
        this.precioUnitario = precioUnitario;
    }
    
    public double getSubtotal()
    {
        return subtotal;
    }
    
    public void setSubtotal(double subtotal)
    {
        this.subtotal = subtotal;
    }
    
    public ventaEntity getVenta()
    {
        return venta;
    }
    
    public void setVenta(ventaEntity venta)
    {
        this.venta = venta;
    }
    
    public productoEntity getProducto()
    {
        return producto;
    }
    
    public void setProducto(productoEntity producto)
    {
        this.producto = producto;
    }
}
