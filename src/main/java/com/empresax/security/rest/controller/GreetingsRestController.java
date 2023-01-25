package com.empresax.security.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import static org.springframework.http.ResponseEntity.status;

@Tag(name = "Greetings", description = "Operations for roles")
@RestController
@RequestMapping(value = "/api/v1/greetings")
public class GreetingsRestController {

    @GetMapping("/user")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> saludo() {
        return status(HttpStatus.OK).body("Hola Usuario");
    }

    @GetMapping("/admin")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> saludoAdmin() {
        return status(HttpStatus.OK).body("Hola Admin");
    }

}
