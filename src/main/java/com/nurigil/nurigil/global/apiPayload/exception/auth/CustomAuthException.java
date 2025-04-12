package com.nurigil.nurigil.global.apiPayload.exception.auth;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;

public class CustomAuthException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public CustomAuthException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }
}
