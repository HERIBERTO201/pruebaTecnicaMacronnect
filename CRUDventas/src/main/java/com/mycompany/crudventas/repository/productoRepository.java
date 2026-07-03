/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.repository;

import com.mycompany.crudventas.entity.productoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 *
 * @author herio
 */
@Repository
public interface productoRepository extends JpaRepository<productoEntity, Long> {
    Optional<productoEntity> findByCodigo(String codigo);
    List<productoEntity> findByNombre(String nombre);
}
