package com.nimshub.softwarearchitecturedemo.auth;

public class VerifyEmailResponse {
    private String message;

    public VerifyEmailResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
