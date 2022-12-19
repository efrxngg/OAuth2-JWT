package com.empresax.security.rest.controller;

import com.empresax.security.domain.dto.RefreshToken;
import com.empresax.security.domain.dto.SignInReq;
import com.empresax.security.domain.dto.SignedInUser;
import com.empresax.security.domain.entity.UserEntity;
import com.empresax.security.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@Tag(name = "User", description = "Operations for users")
@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class AuthRestController {

    private IUserService userService;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> saludo(){
        return status(HttpStatus.OK).body("Hola");
    }

    @PostMapping
    public ResponseEntity<SignedInUser> singUp(@Valid @RequestBody UserEntity user) {
        return status(HttpStatus.CREATED).body(userService.createUser(user).get());
    }

    public ResponseEntity<SignedInUser> signIn(@Valid @RequestBody SignInReq signInReq) {
        return null;
    }

    public ResponseEntity<Void> signOut(@Valid RefreshToken refreshToken) {
        return null;
    }

    public ResponseEntity<SignedInUser> getAccessToken(@Valid RefreshToken refreshToken) {
        return null;
    }

}