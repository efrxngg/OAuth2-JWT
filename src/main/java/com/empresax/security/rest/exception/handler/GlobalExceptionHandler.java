package com.empresax.security.rest.exception.handler;

import com.empresax.security.exception.GenericAlreadyExistsException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.MissingFormatArgumentException;

import static org.springframework.http.ResponseEntity.status;

/**
 * General class in charge of general exception control
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { MismatchedInputException.class })
    public ResponseEntity<ProblemDetail> globalExceptionHandler(MismatchedInputException e,
            HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle("Invalid Refresh Token");
        problemDetail.setType(URI.create(request.getRequestURL().toString()));
        return status(status).body(problemDetail);
    }

    @ExceptionHandler(value = { MissingFormatArgumentException.class })
    public ResponseEntity<ProblemDetail> globalMissingErrorHandler(MissingFormatArgumentException e,
            HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle("Invalid Refresh Token");
        problemDetail.setType(URI.create(request.getRequestURL().toString()));
        return status(status).body(problemDetail);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ProblemDetail> invalidArgumentExcpetion(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
                
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> msg.append(error.getDefaultMessage()).append(", "));
        int len = msg.length();
        msg.delete(len - 2, len);

        var status = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, msg.toString());
        problemDetail.setType(URI.create(request.getRequestURL().toString()));

        return status(status).body(problemDetail);
    }

    @ExceptionHandler(value = { GenericAlreadyExistsException.class })
    public ResponseEntity<ProblemDetail> globalGenericAlreadyExistsException(GenericAlreadyExistsException e,
            HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle("Invalid Refresh Token");
        problemDetail.setType(URI.create(request.getRequestURL().toString()));
        return status(status).body(problemDetail);
    }

}