package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SaveScheduleRequestDto(
        @Size(max = 10)
        @NotBlank String title,
        @NotBlank
        @Size(max = 200)
        String content,
        @NotBlank String password
) {}
