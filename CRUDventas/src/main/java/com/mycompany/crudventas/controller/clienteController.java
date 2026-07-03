/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.controller;

import com.mycompany.crudventas.entity.clienteEntity;
import com.mycompany.crudventas.service.clienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author herio
 */

@RestController
@RequestMapping("/clientes")

public class clienteController {
    
    @Autowired
    private clienteService service;

    @GetMapping
    public List<clienteEntity> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<clienteEntity> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public clienteEntity guardar(@RequestBody clienteEntity cliente) {
        return service.guardar(cliente);
    }

    @PutMapping("/{id}")
    public clienteEntity actualizar(@PathVariable Long id,
        @RequestBody clienteEntity cliente) {
        return service.actualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

}
