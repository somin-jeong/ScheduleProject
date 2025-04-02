package com.example.scheduleProject.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginDto (
        @NotBlank(message = "이메일 입력은 필수입니다.") String email,
        @NotBlank(message = "비밀번호 입력은 필수입니다.") String password
) { }
