package com.empresax.security.exception;

public class InvalidRefreshToken extends RuntimeException {
    public InvalidRefreshToken() {
        super("Invalid Token");
    }
}
