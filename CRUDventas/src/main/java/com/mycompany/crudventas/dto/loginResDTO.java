/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.dto;

/**
 *
 * @author herio
 */
public class loginResDTO {
    
    private boolean success;
    private String usuario;
    private String message;
    private String token;

    public loginResDTO() {}

    public loginResDTO(boolean success, String usuario, String message, String token) {
        this.success = success;
        this.usuario = usuario;
        this.token = token;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
