package com.example.Ecommerce.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExcptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> exception(ProductNotFound exe){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage(exe.getMessage());
        apiResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> exception(MethodArgumentNotValidException exe){
        String errorMessage = exe.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorMessage);
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
