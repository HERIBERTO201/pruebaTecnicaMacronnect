/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.crudventas.dto.loginReqDTO;
import com.mycompany.crudventas.dto.loginResDTO;
import com.mycompany.crudventas.service.loginService;
import javax.validation.Valid;

/**
 *
 * @author herio
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class loginController {
    
    @Autowired
    private loginService service;
    
    @PostMapping("/login")
    public loginResDTO login(@Valid @RequestBody loginReqDTO request) {
        return service.login(request);
    }
    
}
