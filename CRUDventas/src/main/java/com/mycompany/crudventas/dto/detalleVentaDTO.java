/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.dto;

import com.mycompany.crudventas.entity.ventaEntity;
import com.mycompany.crudventas.entity.productoEntity;

/**
 *
 * @author herio
 */
public class detalleVentaDTO {
    
    private ventaEntity venta;
    private productoEntity producto;
    private Long id;
    private int cantidad;
    private double precioUnitario;
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
