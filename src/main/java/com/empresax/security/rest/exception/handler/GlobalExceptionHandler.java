package com.empresax.security.rest.exception.handler;

import com.empresax.security.domain.dto.ErrorMessage;
import com.empresax.security.exception.GenericAlreadyExistsException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.MissingFormatArgumentException;

import static org.springframework.http.ResponseEntity.status;


/**
 * General class in charge of general exception control
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MismatchedInputException.class})
    public ResponseEntity<ErrorMessage> globalExceptionHandler(MismatchedInputException e, WebRequest request) {
        return status(HttpStatus.BAD_REQUEST).body(
                new ErrorMessage(e.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(value = {MissingFormatArgumentException.class})
    public ResponseEntity<ErrorMessage> globalMissingErrorHandler(MissingFormatArgumentException e, WebRequest request) {
        return status(HttpStatus.BAD_REQUEST).body(
                new ErrorMessage(e.getMessage(), request.getDescription(false))
        );
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> globalMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> msg.append(error.getDefaultMessage()).append(", "));
        int len = msg.length();
        msg.delete(len - 2, len);

        System.out.println();
        return status(HttpStatus.BAD_REQUEST).body(
                new ErrorMessage(msg.toString(), request.getDescription(false)));
    }

    @ExceptionHandler(value = {GenericAlreadyExistsException.class})
    public ResponseEntity<ErrorMessage> globalGenericAlreadyExistsException(GenericAlreadyExistsException e, WebRequest request) {
        return status(HttpStatus.BAD_REQUEST).body(
                new ErrorMessage(e.getMessage(), request.getDescription(false))
        );
    }

}