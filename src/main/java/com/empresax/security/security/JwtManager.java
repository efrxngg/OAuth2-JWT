package com.empresax.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.stream.Collectors;

import static com.empresax.security.security.Constants.EXPIRATION_TIME;
import static com.empresax.security.security.Constants.ISSUER;
import static com.empresax.security.security.Constants.ROLE_CLAIM;

@Component
@AllArgsConstructor
public class JwtManager {

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public String create(UserDetails principal) {
        final long time = System.currentTimeMillis();
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(principal.getUsername())
                .withClaim(ROLE_CLAIM, principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .withIssuedAt(new Date(time))
                .withExpiresAt(new Date(time + EXPIRATION_TIME))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

}
