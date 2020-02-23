package com.receipe.api.exception;

import com.mongodb.MongoWriteException;
import com.receipe.api.model.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RecipeExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDTO error = new ErrorDTO();
        error.setErrorCOde(400);
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(error.getErrorCOde()).body(error);
    }

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<ErrorDTO> handleMongoWriteException(MongoWriteException e){
        ErrorDTO error = new ErrorDTO();
        error.setErrorCOde(500);
        error.setMessage(e.getMessage());
        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        ErrorDTO error = new ErrorDTO();
        error.setErrorCOde(500);
        error.setMessage(e.getMessage());
        return ResponseEntity.status(500).body(error);
    }
}
