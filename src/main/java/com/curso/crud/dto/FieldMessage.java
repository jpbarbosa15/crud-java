package com.curso.crud.dto;

public class FieldMessage {
    private String fielNamde;
    private String message;

    public FieldMessage(String fielNamde, String message) {
        this.fielNamde = fielNamde;
        this.message = message;
    }

    public String getFielNamde() {
        return fielNamde;
    }

    public String getMessage() {
        return message;
    }
}
