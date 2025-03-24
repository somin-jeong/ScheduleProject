package com.example.scheduleProject.domain.schedule.dto.response;

import jakarta.validation.constraints.NotNull;

public record ScheduleResponseDto(
        @NotNull Long scheduleId,
        @NotNull String title,
        @NotNull String content,
        @NotNull String authorName,
        @NotNull String password
) {}
