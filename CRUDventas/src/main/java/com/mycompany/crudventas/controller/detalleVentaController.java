/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.controller;

import com.mycompany.crudventas.dto.detalleVentaDTO;
import com.mycompany.crudventas.service.detalleVentaService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author herio
 */
@RestController
@RequestMapping("/detalleventas")
@CrossOrigin(origins = "*")

public class detalleVentaController {
    
    @Autowired
    private detalleVentaService service;

    @GetMapping
    public List<detalleVentaDTO> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<detalleVentaDTO> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public detalleVentaDTO guardar(@Valid @RequestBody detalleVentaDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public detalleVentaDTO actualizar(@Valid @PathVariable Long id,
                                      @RequestBody detalleVentaDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
