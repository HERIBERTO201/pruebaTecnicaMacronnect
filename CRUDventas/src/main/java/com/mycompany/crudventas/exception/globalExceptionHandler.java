/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudventas.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author herio
 */
@RestControllerAdvice
public class globalExceptionHandler {
    @ExceptionHandler(resourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFound(resourceNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());

    }

    @ExceptionHandler(badRequestException.class)
    public ResponseEntity<String> badRequest(badRequestException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationErrors(
            MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){

            errors.put(error.getField(), error.getDefaultMessage());

        }

        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> general(Exception ex){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());

    }

}
