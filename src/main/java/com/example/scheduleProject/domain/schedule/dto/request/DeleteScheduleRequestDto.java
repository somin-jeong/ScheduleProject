package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DeleteScheduleRequestDto(
        @NotBlank String password
) {}