package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateScheduleRequestDto(
        String title,
        @Size(max = 200)
        String content,
        String authorName,
        @NotBlank String password
) {}
