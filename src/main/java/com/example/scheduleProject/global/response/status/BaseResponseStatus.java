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
    NOT_EXIST_EMAIL_ERROR(3001, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이메일입니다."),
    INVALID_PASSWORD_ERROR(3002, HttpStatus.BAD_REQUEST.value(), "비밀번호가 올바르지 않습니다."),
    NOT_EXIST_USER_ERROR(3003, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다."),
    FAIL_USER_DELETE_ERROR(3004, HttpStatus.INTERNAL_SERVER_ERROR.value(), "사용자 삭제에 실패했습니다."),

    /**
     * 4000: 일정 정보 오류
     */
    NOT_EXIST_SCHEDULE_ERROR(4001, HttpStatus.BAD_REQUEST.value(), "일정이 존재하지 않습니다."),
    NOT_PASSWORD_MATCH(4002, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    FAIL_SCHEDULE_DELETE_ERROR(4003, HttpStatus.INTERNAL_SERVER_ERROR.value(), "일정 삭제에 실패했습니다."),
    NOT_ALLOW_UPDATE_SCHEDULE_ERROR(4004, HttpStatus.BAD_REQUEST.value(), "일정 작성자만 일정을 수정할 수 있습니다."),
    NOT_ALLOW_DELETE_SCHEDULE_ERROR(4005, HttpStatus.BAD_REQUEST.value(), "일정 작성자만 일정을 삭제할 수 있습니다."),

    /**
     * 5000: 댓글 정보 오류
     */
    NOT_EXIST_COMMENT_ERROR(5001, HttpStatus.BAD_REQUEST.value(), "댓글이 존재하지 않습니다."),
    FAIL_COMMENT_DELETE_ERROR(5002, HttpStatus.INTERNAL_SERVER_ERROR.value(), "댓글 삭제에 실패했습니다."),
    NOT_ALLOW_UPDATE_COMMENT_ERROR(5003, HttpStatus.BAD_REQUEST.value(), "댓글 작성자만 댓글을 수정할 수 있습니다."),
    NOT_ALLOW_DELETE_COMMENT_ERROR(5004, HttpStatus.BAD_REQUEST.value(), "댓글 작성자만 댓글을 삭제할 수 있습니다.");



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
