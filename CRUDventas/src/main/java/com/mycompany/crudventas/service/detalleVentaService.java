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
import org.springframework.stereotype.Service;
import com.mycompany.crudventas.exception.resourceNotFoundException;

/**
 *
 * @author herio
 */
@Service
public class detalleVentaService {

    @Autowired
    private detalleVentaRepository repository;
    @Autowired
    private ventaRepository ventaRepository;
    @Autowired
    private productoRepository productoRepository;

    public List<detalleVentaDTO> obtenerTodos() {
        List<detalleVentaEntity> detalles = repository.findAll();
        List<detalleVentaDTO> dto = new ArrayList<>();
        for (detalleVentaEntity detalle : detalles) {
            dto.add(toDTO(detalle));
        }
        return dto;
    }

    public Optional<detalleVentaDTO> obtenerPorId(Long id) {
        Optional<detalleVentaEntity> detalle = repository.findById(id);
        if (!detalle.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(detalle.get()));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public detalleVentaDTO guardar(detalleVentaDTO dto) {

        detalleVentaEntity detalle = toEntity(dto);

        detalle = repository.save(detalle);

        return toDTO(detalle);
    }

    public detalleVentaDTO actualizar(Long id, detalleVentaDTO dto) {

        Optional<detalleVentaEntity> optional = repository.findById(id);

        if (!optional.isPresent()) {
            return null;
        }

        detalleVentaEntity detalle = toEntity(dto);
        detalle.setId(id);

        detalle = repository.save(detalle);

        return toDTO(detalle);
    }
    
    private detalleVentaDTO toDTO(detalleVentaEntity venta) {
        detalleVentaDTO dto = new detalleVentaDTO();
        dto.setId(venta.getId());
        dto.setCantidad(venta.getCantidad());
        dto.setPrecioUnitario(venta.getPrecioUnitario());
        dto.setProductoID(venta.getProducto().getId());
        dto.setSubtotal(venta.getSubtotal());
        dto.setVentaID(venta.getVenta().getId());
        return dto;
    }

    private detalleVentaEntity toEntity(detalleVentaDTO dto) {
        detalleVentaEntity detalle = new detalleVentaEntity();
        ventaEntity venta = ventaRepository.findById(dto.getVentaID())
                .orElseThrow(() -> new resourceNotFoundException("Venta no encontrada"));
        productoEntity producto = productoRepository.findById(dto.getProductoID())
                .orElseThrow(() -> new resourceNotFoundException("Producto no encontrado"));
        detalle.setVenta(venta);
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setSubtotal(dto.getSubtotal());
        return detalle;
    }
}
