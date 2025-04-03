package com.example.scheduleProject.domain.schedule.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleResponseDto(
        @NotNull Long scheduleId,
        @NotNull String title,
        @NotNull String content,
        @NotNull Long commentCount,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull LocalDateTime updatedAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull LocalDateTime createdAt,
        @NotNull String authorName
) {}
