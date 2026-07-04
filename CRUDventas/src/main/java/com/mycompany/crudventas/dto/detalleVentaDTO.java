/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.dto;


/**
 *
 * @author herio
 */
public class detalleVentaDTO {
    
    private Long ventaID;
    private Long productoID;
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
    
    public Long getVentaID()
    {
        return ventaID;
    }
    
    public void setVentaID(Long ventaID)
    {
        this.ventaID = ventaID;
    }
    
    public Long getProductoID()
    {
        return productoID;
    }
    
    public void setProductoID(Long productoID)
    {
        this.productoID = productoID;
    }
}
