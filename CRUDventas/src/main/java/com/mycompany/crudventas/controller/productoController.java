/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.controller;

import com.mycompany.crudventas.dto.productoDTO;
import com.mycompany.crudventas.service.productoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author herio
 */

@RestController
@RequestMapping("/productos")

public class productoController {

    @Autowired
    private productoService service;
    
    @GetMapping
    public List<productoDTO> obtenerTodos() {
        return service.obtenerTodos();
    }
    
    @GetMapping("/nombre/{nombre}")
    public List<productoDTO> obtenerPorNombre(@PathVariable String nombre) {
        return service.obtenerPorNombre(nombre);
    }
    
    @GetMapping("/codigo/{codigo}")
    public Optional<productoDTO> obtenerPorCodigo(@PathVariable String codigo) {
        return service.obtenerPorCodigo(codigo);
    }
    
    @PostMapping
    public productoDTO guardar(@RequestBody productoDTO dto) {
        return service.guardar(dto);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
