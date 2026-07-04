/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.service;

import com.mycompany.crudventas.dto.detalleVentaDTO;
import java.util.List;
import com.mycompany.crudventas.dto.ventaDTO;
import com.mycompany.crudventas.entity.clienteEntity;
import com.mycompany.crudventas.entity.detalleVentaEntity;
import com.mycompany.crudventas.entity.productoEntity;
import com.mycompany.crudventas.entity.ventaEntity;
import com.mycompany.crudventas.entity.ventastatus;
import com.mycompany.crudventas.repository.clienteRepository;
import com.mycompany.crudventas.repository.detalleVentaRepository;
import com.mycompany.crudventas.repository.productoRepository;
import com.mycompany.crudventas.repository.ventaRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author herio
 */
@Service
public class ventaService {

    @Autowired
    private ventaRepository repository;

    @Autowired
    private clienteRepository clienteRepository;

    @Autowired
    private productoRepository productoRepository;

    @Autowired
    private detalleVentaRepository detalleVentaRepository;

    public List<ventaDTO> obtenerTodos() {
        List<ventaEntity> ventas = repository.findAll();
        List<ventaDTO> ventaDTO = new ArrayList<>();
        for (ventaEntity venta : ventas) {
            ventaDTO.add(toDTO(venta));
        }
        return ventaDTO;
    }

    ;
    
    
    public Optional<ventaDTO> obtenerPorFolio(String folio) {
        Optional<ventaEntity> venta = repository.findByFolio(folio);
        if (!venta.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(venta.get()));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public ventaDTO guardar(ventaDTO dto) {
        ventaEntity venta = toEntity(dto);
        venta = repository.save(venta);
        return dto;
    }

    public ventaDTO actualizar(Long id, ventaDTO dto) {
        Optional<ventaEntity> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        return dto;
    }

    @Transactional
    public ventaDTO registrarVenta(ventaDTO dto) {

        clienteEntity cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        ventaEntity venta = new ventaEntity();

        venta.setClienteId(cliente);

        venta.setFecha(new Date());
        venta.setEstado(ventastatus.ACTIVO);
        venta.setFolio("TEMP");
        venta.setTotal(0.0);
        venta = repository.save(venta);
        venta.setFolio("V" + String.format("%06d", venta.getId()));
        double total = 0;
        for (detalleVentaDTO detalleDTO : dto.getListDetalleVenta()) {
            productoEntity producto = productoRepository.findById(detalleDTO.getProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new RuntimeException(
                        "No existe stock suficiente para " + producto.getNombre());
            }
            detalleVentaEntity detalle = new detalleVentaEntity();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            double subtotal = detalleDTO.getCantidad() * producto.getPrecio();
            detalle.setSubtotal(subtotal);
            total += subtotal;
            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);
            detalleVentaRepository.save(detalle);
        }
        venta.setTotal(total);
        repository.save(venta);
        return toDTO(venta);
    }

    private ventaDTO toDTO(ventaEntity venta) {
        ventaDTO dto = new ventaDTO();
        dto.setClienteId(venta.getClienteId().getId());
        dto.setEstado(venta.getEstado());
        dto.setFecha(venta.getFecha());
        dto.setFolio(venta.getFolio());
        dto.setId(venta.getId());
        dto.setTotal(venta.getTotal());

        return dto;
    }

    private ventaEntity toEntity(ventaDTO dto) {
        ventaEntity venta = new ventaEntity();
        clienteEntity cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        venta.setClienteId(cliente);
        venta.setEstado(dto.getEstado());
        venta.setFecha(dto.getFecha());
        venta.setFolio(dto.getFolio());
        venta.setId(dto.getId());
        venta.setTotal(dto.getTotal());
        return venta;
    }
}
