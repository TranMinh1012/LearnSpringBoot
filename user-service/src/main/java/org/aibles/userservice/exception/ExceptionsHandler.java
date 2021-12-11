package org.aibles.userservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {AbstractException.class})
    public ResponseEntity<String> handlerException(AbstractException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }


}
