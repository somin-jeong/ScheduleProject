package com.example.scheduleProject.global.exception;

import com.example.scheduleProject.global.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ResponseStatus exceptionStatus;

    public BaseException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}