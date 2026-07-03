/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.service;

import com.mycompany.crudventas.dto.productoDTO;
import com.mycompany.crudventas.entity.productoEntity;
import com.mycompany.crudventas.repository.productoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 *
 * @author herio
 */
@Service
public class productoService {

    @Autowired
    private productoRepository repository;

    public List<productoDTO> obtenerTodos() {
        List<productoEntity> productos = repository.findAll();
        List<productoDTO> productoDTO = new ArrayList<>();
        for (productoEntity producto : productos) {
            productoDTO.add(toDTO(producto));
        }
        return productoDTO;
    }

    public productoDTO guardar(productoDTO dto) {
        productoEntity producto = toEntity(dto);
        producto = repository.save(producto);
        return dto;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<productoDTO> obtenerPorCodigo(String codigo) {
        Optional<productoEntity> producto = repository.findByCodigo(codigo);
        if (!producto.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(producto.get()));
    }

    public List<productoDTO> obtenerPorNombre(String nombre) {
        List<productoEntity> productos = repository.findByNombre(nombre);
        List<productoDTO> productosDTO = new ArrayList<>();
        for (productoEntity producto : productos) {
            productosDTO.add(toDTO(producto));
        }
        return productosDTO;
    }

    public productoDTO actualizar(Long id, productoDTO dto) {
        Optional<productoEntity> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        productoEntity producto = optional.get();
        producto.setNombre(dto.getNombre());
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto = repository.save(producto);
        return toDTO(producto);
    }

    private productoDTO toDTO(productoEntity producto) {
        productoDTO dto = new productoDTO();
        dto.setId(producto.getId());
        dto.setCodigo(producto.getCodigo());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setDescripcion(producto.getDescripcion());
        return dto;
    }

    private productoEntity toEntity(productoDTO dto) {
        productoEntity producto = new productoEntity();
        producto.setId(dto.getId());
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return producto;
    }
}
