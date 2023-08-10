package com.register.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.register.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handlerResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.CONFLICT).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.CONFLICT);

    }
	
	@ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse> handlerGeneralException(GeneralException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.CONFLICT).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.CONFLICT);

    }
}
