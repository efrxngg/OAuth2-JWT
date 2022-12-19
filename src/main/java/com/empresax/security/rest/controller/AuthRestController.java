package com.empresax.security.rest.controller;

import com.empresax.security.domain.dto.RefreshToken;
import com.empresax.security.domain.dto.SignInReq;
import com.empresax.security.domain.dto.SignedInUser;
import com.empresax.security.domain.entity.UserEntity;
import com.empresax.security.exception.InvalidRefreshToken;
import com.empresax.security.service.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Tag(name = "User", description = "Operations for users")
@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class AuthRestController {

    private IUserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> saludo() {
        return status(HttpStatus.OK).body("Hola");
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<SignedInUser> singUp(@Valid @RequestBody UserEntity user) {
        return status(HttpStatus.CREATED).body(userService.createUser(user)
                .orElseThrow(InvalidRefreshToken::new));
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<SignedInUser> signIn(@Valid @RequestBody SignInReq signInReq) {
        UserEntity userEntity = userService.findUserByUsername(signInReq.getUsername());

        if (!passwordEncoder.matches(signInReq.getPassword(), userEntity.getPassword()))
            throw new InsufficientAuthenticationException("Unauthorized");

        return ok(userService.getSignedInUser(userEntity));
    }

    @PostMapping(value = "/sign-out")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> signOut(@Valid @RequestBody RefreshToken refreshToken) {
        userService.removeRefreshToken(refreshToken);
        return status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/refresh")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SignedInUser> getAccessToken(@Valid @RequestBody RefreshToken refreshToken) {
        return ok(userService.getAccessToken(refreshToken).orElseThrow(InvalidRefreshToken::new));
    }

}