package com.example.scheduleProject.global.exception;

import com.example.scheduleProject.global.response.status.ResponseStatus;

public class CommentException extends BaseException {

    public CommentException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
