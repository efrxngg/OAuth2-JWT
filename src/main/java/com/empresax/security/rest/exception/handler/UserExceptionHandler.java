package com.empresax.security.rest.exception.handler;

import com.empresax.security.domain.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.ResponseEntity.status;


@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ErrorMessage> usernameNotFoundExceptionHandler(UsernameNotFoundException e, WebRequest request) {
        return status(HttpStatus.NOT_FOUND).body(
                new ErrorMessage(e.getMessage(), request.getDescription(false)));

    }

}
