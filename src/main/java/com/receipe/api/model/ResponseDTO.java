package com.receipe.api.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDTO {
    private HttpStatus status;
    private String message;

    public ResponseDTO(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
