package com.example.scheduleProject.global.exception.handler;

import com.example.scheduleProject.global.exception.UserException;
import com.example.scheduleProject.global.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserException.class})
    public BaseErrorResponse handle_BaseException(UserException e) {
        log.error("[handle_BadRequest]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }
}
