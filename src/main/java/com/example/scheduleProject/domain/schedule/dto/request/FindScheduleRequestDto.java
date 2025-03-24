package com.example.scheduleProject.domain.schedule.dto.request;

public record FindScheduleRequestDto(
        String updatedDate,
        String authorName
) {}