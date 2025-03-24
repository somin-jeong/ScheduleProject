package com.example.scheduleProject.domain.schedule.dto.response;

import jakarta.validation.constraints.NotNull;

public record SaveScheduleResponseDto(
        @NotNull Long scheduleId
) {}