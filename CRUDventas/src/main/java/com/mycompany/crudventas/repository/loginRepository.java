/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.crudventas.entity.loginEntity;
/**
 *
 * @author herio
 */

@Repository
public interface loginRepository extends JpaRepository<loginEntity, Integer>{
    Optional<loginEntity> findByUsuario(String usuario);
}
