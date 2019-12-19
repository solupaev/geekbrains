package com.geekbrains.gwt.common;

public class ErrorDto {
    private int statusCode;
    private String message;

    public ErrorDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorDto() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
