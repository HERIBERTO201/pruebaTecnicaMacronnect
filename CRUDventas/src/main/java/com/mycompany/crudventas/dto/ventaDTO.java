/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.dto;

import com.mycompany.crudventas.entity.clienteEntity;
import com.mycompany.crudventas.entity.ventastatus;
import java.util.Date;
     
/**
 *
 * @author herio
 */
public class ventaDTO {

    private Long id;
    private Date fecha;
    private Double total;
    private String folio;
    private clienteEntity cliente;
    private ventastatus estado;

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

    public clienteEntity getClienteId() {
        return cliente;
    }

    public void setClienteId(clienteEntity cliente) {
        this.cliente = cliente;
    }

    public ventastatus getEstado() {
        return estado;
    }

    public void setEstado(ventastatus estado) {
        this.estado = estado;
    }
}
