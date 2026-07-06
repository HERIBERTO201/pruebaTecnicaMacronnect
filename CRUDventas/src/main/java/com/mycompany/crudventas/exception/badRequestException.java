/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.exception;

/**
 *
 * @author herio
 */
public class badRequestException extends RuntimeException{
    public badRequestException(String mensaje) {
        super(mensaje);
    }
}
