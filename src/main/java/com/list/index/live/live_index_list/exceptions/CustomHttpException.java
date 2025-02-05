package com.list.index.live.live_index_list.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class CustomHttpException extends RuntimeException {

    private final HttpStatus status;

    protected CustomHttpException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomHttpException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public CustomHttpException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public CustomHttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    public CustomHttpException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}