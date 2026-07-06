/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.service;
import com.mycompany.crudventas.dto.loginReqDTO;
import com.mycompany.crudventas.dto.loginResDTO;
import com.mycompany.crudventas.entity.loginEntity;
import com.mycompany.crudventas.repository.loginRepository;
import com.mycompany.crudventas.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 *
 * @author herio
 */
@Service
public class loginService {
    
    @Autowired
    private loginRepository repo;
    @Autowired
    private JwtService jwtService;

    public loginResDTO login(loginReqDTO request) {

        Optional<loginEntity> userOpt = repo.findByUsuario(request.getUsuario());

        if (!userOpt.isPresent()) {
            return new loginResDTO(false, null, "Usuario no existe", null);
        }

        loginEntity user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new loginResDTO(false, null, "Password incorrecto", null);
        }

        String token = jwtService.generarToken(user.getUsuario());
        return new loginResDTO(true, user.getUsuario(),"Login exitoso", token);
    }
}
