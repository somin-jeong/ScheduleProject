package com.example.scheduleProject.global.exception;

import com.example.scheduleProject.global.response.status.ResponseStatus;

public class UserException extends BaseException {
    public UserException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
