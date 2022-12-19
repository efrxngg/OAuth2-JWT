package com.empresax.security.rest.exception.handler;

import com.empresax.security.domain.dto.ErrorMessage;
import com.empresax.security.exception.InvalidRefreshToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.ResponseEntity.*;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = {InsufficientAuthenticationException.class})
    public ResponseEntity<ErrorMessage> globalInsufficientAuth(InsufficientAuthenticationException e, WebRequest request) {
        return status(HttpStatus.UNAUTHORIZED).body(
                new ErrorMessage(
                        e.getMessage(),
                        request.getDescription(false))
        );
    }

    @ExceptionHandler(value = {InvalidRefreshToken.class})
    public ResponseEntity<ErrorMessage> globalInvalidRefreshToken(InvalidRefreshToken e, WebRequest request) {
        return status(HttpStatus.UNAUTHORIZED).body(
                new ErrorMessage(
                        e.getMessage(),
                        request.getDescription(false))
        );
    }

}
