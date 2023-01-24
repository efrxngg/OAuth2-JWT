package com.empresax.security.rest.exception.handler;

import com.empresax.security.exception.InvalidRefreshToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = {InsufficientAuthenticationException.class})
    public ResponseEntity<ProblemDetail> globalInsufficientAuth(InsufficientAuthenticationException e, HttpServletRequest request) {
        var status = HttpStatus.UNAUTHORIZED;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle("Insufficient Authentication");
        problemDetail.setType(URI.create(request.getRequestURL().toString()));
        return status(status).body(problemDetail);
    }

    @ExceptionHandler(value = {InvalidRefreshToken.class})
    public ResponseEntity<ProblemDetail> globalInvalidRefreshToken(InvalidRefreshToken e, HttpServletRequest request) {
        var status = HttpStatus.UNAUTHORIZED;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle("Invalid Refresh Token");
        problemDetail.setType(URI.create(request.getRequestURL().toString()));
        return status(status).body(problemDetail);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ProblemDetail> usernameNotFoundExceptionHandler(UsernameNotFoundException e, HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle("Username not found");
        problemDetail.setType(URI.create(request.getRequestURL().toString()));
        return status(status).body(problemDetail);
    }

}
