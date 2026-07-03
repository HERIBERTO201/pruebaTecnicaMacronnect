/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.service;
import com.mycompany.crudventas.entity.clienteEntity;
import com.mycompany.crudventas.repository.clienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author herio
 */
@Service
public class clienteService {
    
    @Autowired
    private clienteRepository repository;

    public List<clienteEntity> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<clienteEntity> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public clienteEntity guardar(clienteEntity cliente) {
        return repository.save(cliente);
    }

    public clienteEntity actualizar(Long id, clienteEntity cliente) {
        cliente.setId(id);
        return repository.save(cliente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
