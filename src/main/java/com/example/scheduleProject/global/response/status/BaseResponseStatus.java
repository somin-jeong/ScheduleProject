package com.example.scheduleProject.global.response.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseResponseStatus implements ResponseStatus {
    /**
     * 1000: 요청 성공 (OK)
     */
    SUCCESS(1000, HttpStatus.OK.value(), "요청에 성공하였습니다."),

    /**
     * 3000: 사용자 정보 오류
     */
    NOT_EXIST_USER_ERROR(3001, HttpStatus.OK.value(), "존재하지 않는 사용자입니다.");

    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
