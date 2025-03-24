package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateScheduleRequestDto(
        String title,
        String content,
        String authorName,
        @NotNull String password
) {}
