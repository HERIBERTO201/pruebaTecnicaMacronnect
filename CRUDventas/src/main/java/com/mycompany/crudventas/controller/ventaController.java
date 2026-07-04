/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.controller;

import com.mycompany.crudventas.dto.ventaDTO;
import com.mycompany.crudventas.service.ventaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author herio
 */

@RestController
@RequestMapping("/ventas")
public class ventaController {

    @Autowired
    private ventaService service;

    @GetMapping
    public List<ventaDTO> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<ventaDTO> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ventaDTO guardar(@RequestBody ventaDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public ventaDTO actualizar(@PathVariable Long id,
            @RequestBody ventaDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/folio/{folio}")
    public Optional<ventaDTO> obtenerPorFolio(@PathVariable String folio) {
        return service.obtenerPorFolio(folio);
    }

    @PostMapping("/registrar")
    public ventaDTO registrarVenta(@RequestBody ventaDTO dto) {
        return service.registrarVenta(dto);
    }

}
