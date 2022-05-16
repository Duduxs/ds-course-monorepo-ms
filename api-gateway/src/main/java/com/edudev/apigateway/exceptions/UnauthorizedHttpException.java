package com.edudev.apigateway.exceptions;

public final class UnauthorizedHttpException extends RuntimeException {

    public UnauthorizedHttpException() {
        this("Entity not authorized!");
    }

    public UnauthorizedHttpException(final String msg) {
        super(msg);
    }
}
