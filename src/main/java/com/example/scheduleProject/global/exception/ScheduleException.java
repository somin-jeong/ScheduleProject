package com.example.scheduleProject.global.exception;

import com.example.scheduleProject.global.response.status.ResponseStatus;

public class ScheduleException extends BaseException {
    public ScheduleException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
