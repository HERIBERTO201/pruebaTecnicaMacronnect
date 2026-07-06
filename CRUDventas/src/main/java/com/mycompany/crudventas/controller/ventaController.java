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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author herio
 */

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")

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

    @PutMapping("/{id}")
    public ventaDTO actualizar(@Valid @PathVariable Long id,
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
    public ventaDTO registrarVenta(@Valid @RequestBody ventaDTO dto) {
        return service.registrarVenta(dto);
    }

}
