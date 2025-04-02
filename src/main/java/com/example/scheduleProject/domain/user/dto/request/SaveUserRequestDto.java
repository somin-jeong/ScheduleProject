package com.example.scheduleProject.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SaveUserRequestDto (
        @NotBlank String name,
        @NotBlank String email
) { }
