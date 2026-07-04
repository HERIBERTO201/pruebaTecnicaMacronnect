/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.service;

import java.util.List;
import java.util.Optional;
import com.mycompany.crudventas.dto.detalleVentaDTO;
import com.mycompany.crudventas.entity.detalleVentaEntity;
import com.mycompany.crudventas.entity.productoEntity;
import com.mycompany.crudventas.entity.ventaEntity;
import com.mycompany.crudventas.repository.detalleVentaRepository;
import com.mycompany.crudventas.repository.productoRepository;
import com.mycompany.crudventas.repository.ventaRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author herio
 */
public class detalleVentaService {
    
    @Autowired
    private detalleVentaRepository repository;
    @Autowired
    private ventaRepository ventaRepository;
    @Autowired
    private productoRepository productoRepository;
    
    public List<detalleVentaDTO> obtenerTodos()
    {
        List<detalleVentaEntity> ventas = repository.findAll();
        List<detalleVentaDTO> ventaDTO = new ArrayList<>();
        for (detalleVentaEntity venta : ventas) {
            ventaDTO.add(toDTO(venta));
        }
        return ventaDTO;
    }

    public Optional<detalleVentaDTO> obtenerPorId(Long id)
    {
        Optional<detalleVentaEntity> venta = repository.findById(id);
        if (!venta.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(venta.get()));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    
    private detalleVentaDTO toDTO(detalleVentaEntity venta) {
        detalleVentaDTO dto = new detalleVentaDTO();
        dto.setId(venta.getId());
        dto.setCantidad(venta.getCantidad());
        dto.setPrecioUnitario(venta.getPrecioUnitario());
        dto.setProducto(venta.getProducto().getId());
        dto.setSubtotal(venta.getSubtotal());
        dto.setVenta(venta.getVenta().getId());
        return dto;
    }
    
    private detalleVentaEntity toEntity(detalleVentaDTO dto){
    detalleVentaEntity detalle = new detalleVentaEntity();
    ventaEntity venta = ventaRepository.findById(dto.getVenta())
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    productoEntity producto = productoRepository.findById(dto.getProducto())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    detalle.setVenta(venta);
    detalle.setProducto(producto);
    detalle.setCantidad(dto.getCantidad());
    detalle.setPrecioUnitario(dto.getPrecioUnitario());
    detalle.setSubtotal(dto.getSubtotal());
    return detalle;
    }
}
