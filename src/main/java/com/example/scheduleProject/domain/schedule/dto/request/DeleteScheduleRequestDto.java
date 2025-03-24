package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.NotNull;

public record DeleteScheduleRequestDto(
        @NotNull String password
) {}