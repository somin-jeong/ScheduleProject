package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.NotNull;

public record SaveScheduleRequestDto(
        @NotNull Long userId,
        @NotNull String title,
        @NotNull String content,
        @NotNull String password
) {}
