package com.aboitizpower.app.exception;

public class DevSecOpsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DevSecOpsException (String message, Throwable cause) {
        super(message, cause);
    }
}
