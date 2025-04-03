package com.example.scheduleProject.domain.schedule.dto.request;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record FindScheduleRequestDto(
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜는 yyyy-MM-dd 형식이어야 합니다.")
        String updatedDate,
        String authorName,
        int page,
        int size
) {
        public LocalDateTime getStartOfDay() {
                return updatedDate == null ? null : LocalDate.parse(updatedDate).atStartOfDay();
        }

        public LocalDateTime getEndOfDay() {
                return updatedDate == null ? null : LocalDate.parse(updatedDate).atTime(LocalTime.MAX);
        }
}