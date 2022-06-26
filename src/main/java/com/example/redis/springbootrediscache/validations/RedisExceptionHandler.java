package com.example.redis.springbootrediscache.validations;

import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class RedisExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(@NonNull ResourceNotFoundException ex) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage(), Instant.now(),
                Optional.ofNullable(attr).map(ServletRequestAttributes::getRequest).map(HttpServletRequest::getRequestURI).orElse(""),
                HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String path = Optional.ofNullable(attr).map(ServletRequestAttributes::getRequest).map(HttpServletRequest::getRequestURI).orElse("");
        String errorMessage = ex.getBindingResult().getAllErrors().stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse("");
        return ResponseEntity.badRequest().body(new ErrorResponse(errorMessage, Instant.now(), path, status));
    }

}