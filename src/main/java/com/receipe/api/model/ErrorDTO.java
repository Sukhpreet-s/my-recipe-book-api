package com.receipe.api.model;

public class ErrorDTO {

    private int errorCOde;
    private String message;

    public int getErrorCOde() {
        return errorCOde;
    }

    public void setErrorCOde(int errorCOde) {
        this.errorCOde = errorCOde;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
