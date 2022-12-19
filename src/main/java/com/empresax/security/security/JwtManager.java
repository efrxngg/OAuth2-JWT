package com.empresax.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtManager {

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public String create(UserDetails principal) {
        final long time = System.currentTimeMillis();
        return JWT.create()
                .withIssuer("empresax")
                .withSubject(principal.getUsername())
                .withClaim("roles", principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .withIssuedAt(new Date(time))
                .withExpiresAt(new Date(time + 360000))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

}
