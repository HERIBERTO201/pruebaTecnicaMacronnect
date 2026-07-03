/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.service;

import com.mycompany.crudventas.dto.clienteDTO;
import com.mycompany.crudventas.entity.clienteEntity;
import com.mycompany.crudventas.repository.clienteRepository;
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
public class clienteService {

    @Autowired
    private clienteRepository repository;

    public List<clienteDTO> obtenerTodos() {
        List<clienteEntity> clientes = repository.findAll();
        List<clienteDTO> clientesDTO = new ArrayList<>();
        for (clienteEntity cliente : clientes) {
            clientesDTO.add(toDTO(cliente));
        }
        return clientesDTO;
    }

    public Optional<clienteDTO> obtenerPorId(Long id) {
        Optional<clienteEntity> cliente = repository.findById(id);
        if (!cliente.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(cliente.get()));
    }

    public clienteDTO guardar(clienteDTO dto) {
        clienteEntity cliente = toEntity(dto);
        cliente = repository.save(cliente);
        return dto;
    }

    public clienteDTO actualizar(Long id, clienteDTO dto) {
        Optional<clienteEntity> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        clienteEntity cliente = optional.get();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente = repository.save(cliente);
        return toDTO(cliente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private clienteDTO toDTO(clienteEntity cliente) {
        clienteDTO dto = new clienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        return dto;
    }

    private clienteEntity toEntity(clienteDTO dto) {
        clienteEntity cliente = new clienteEntity();
        cliente.setId(dto.getId());
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        return cliente;
    }

}
