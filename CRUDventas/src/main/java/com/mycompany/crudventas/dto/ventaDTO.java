/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.dto;

import com.mycompany.crudventas.entity.ventastatus;
import java.util.Date;
import java.util.List;
     
/**
 *
 * @author herio
 */
public class ventaDTO {

    private Long id;
    private Date fecha;
    private Double total;
    private String folio;
    private Long clienteID;
    private ventastatus estado;
    private List<detalleVentaDTO> detalles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Long getClienteId() {
        return clienteID;
    }

    public void setClienteId(Long clienteID) {
        this.clienteID = clienteID;
    }

    public ventastatus getEstado() {
        return estado;
    }

    public void setEstado(ventastatus estado) {
        this.estado = estado;
    }
    
    public List<detalleVentaDTO> getListDetalleVenta()
    {
        return detalles;
    }
    
    public void setListDetalleVenta(List<detalleVentaDTO> detalles)
    {
        this.detalles = detalles;
    }
}
