package com.designpattern.mvvm.model;

public class Result {

    private String message;
    private String status;

    public Result() {
    }

    public Result(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}