package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.Pattern;

public record FindScheduleRequestDto(
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜는 yyyy-MM-dd 형식이어야 합니다.")
        String updatedDate,
        Long userId
) {}