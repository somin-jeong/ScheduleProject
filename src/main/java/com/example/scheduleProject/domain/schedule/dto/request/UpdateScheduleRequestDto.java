package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateScheduleRequestDto(
        @Size(max = 10)
        String title,
        @Size(max = 200)
        String content,
        @NotBlank String password
) {}
