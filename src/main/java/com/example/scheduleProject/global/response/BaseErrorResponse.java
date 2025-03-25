package com.example.scheduleProject.global.response;

import com.example.scheduleProject.global.response.status.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"code", "status", "message", "timestamp"})
public class BaseErrorResponse implements ResponseStatus {
    private int code;
    private int status;
    private String message;

    public BaseErrorResponse(ResponseStatus status){
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
    }

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
