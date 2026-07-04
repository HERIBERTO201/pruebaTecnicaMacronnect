/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

/**
 *
 * @author herio
 */
@Entity
@Table(name = "venta")
public class ventaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fecha;

    @NotNull
    @DecimalMin(value = "0.0")
    private Double total;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String folio;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private clienteEntity cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<detalleVentaEntity> detalles;
    
    @NotNull
    @Enumerated(EnumType.STRING)
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
