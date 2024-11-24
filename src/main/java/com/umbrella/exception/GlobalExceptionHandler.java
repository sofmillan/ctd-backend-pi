package com.umbrella.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> resourceNotFound(ResourceNotFoundException e){
        ApiException apiException = new ApiException(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND, 404);
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(apiException);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiException> resourceAlreadyExist(ResourceAlreadyExistException e){
        ApiException apiException = new ApiException(e.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT, 409);
        return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(apiException);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiException> handleMultipartException(MultipartException e) {
        ApiException apiException = new ApiException(e.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST, 404);
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(apiException);
    }
}
