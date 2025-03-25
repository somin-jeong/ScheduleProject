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
     * 2000: 요청 실패
     */
    ARGUMENT_TYPE_MISMATCH(2001, HttpStatus.BAD_REQUEST.value(), "잘못된 파라미터 타입입니다."),

    /**
     * 3000: 사용자 정보 오류
     */
    NOT_EXIST_USER_ERROR(3001, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다."),

    /**
     * 4000: 일정 정보 오류
     */
    NOT_EXIST_SCHEDULE_ERROR(4001, HttpStatus.BAD_REQUEST.value(), "일정이 존재하지 않습니다."),
    NOT_PASSWORD_MATCH(4002, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다.");

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
