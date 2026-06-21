package com.codingshuttle.projects.lovable_clone.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,  e.getMessage());
        log.error(apiError.toString(), e);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,  e.getResourceName()+ " with id "+ e.getResourceId() +" not found");
        log.error(apiError.toString(), e);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

         List<ApiFieldError> apiFieldError = e.getBindingResult().getFieldErrors().stream()
                 .map(fieldError -> new ApiFieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                 .toList();

         ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,  "Input Validation Failed", apiFieldError);
        log.error(apiError.toString(), e);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }
}
