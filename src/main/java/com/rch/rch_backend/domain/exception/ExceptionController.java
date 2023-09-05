package com.rch.rch_backend.domain.exception;

import com.rch.rch_backend.domain.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e){

        ErrorResponse errorResponse = ErrorResponse.builder().code("400").message("잘못된 요청입니다.").validation(new HashMap<>()).build();

        List<FieldError> fieldErrors = e.getFieldErrors();

        fieldErrors.stream().forEach(error -> {
            errorResponse.addValidation(error.getField(), error.getDefaultMessage());
        });
        return errorResponse;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse runtimeExceptionHandler(UsernameNotFoundException e){
        ErrorResponse errorResponse = ErrorResponse.builder().code("400").message(e.getMessage()).validation(new HashMap<>()).build();

        return errorResponse;
    }
}
