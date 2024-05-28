package com.example.api.exception;


import com.example.api.controller.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    public ApiResponse<Void> handleNoSuchElementException(NoSuchElementException exception) {
        return ApiResponse.error(HttpStatus.NOT_FOUND, exception.getMessage());
    }


    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusinessException(BusinessException exception) {
        return ApiResponse.error(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler(BadParameterException.class)
    public ApiResponse<Void> handleBadParameterException(BadParameterException badParameterException) {
        List<String> messageList = badParameterException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return ApiResponse.error(HttpStatus.BAD_REQUEST, String.join("\n", messageList));
    }

}
