package com.list.index.live.live_index_list.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomHttpException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
