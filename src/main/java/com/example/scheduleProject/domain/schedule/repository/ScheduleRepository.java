package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.PageResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;

import java.util.Optional;

public interface ScheduleRepository {
    SaveScheduleResponseDto saveSchedule(Schedule schedule);
    PageResponseDto<ScheduleResponseDto> findAllSchedules(String updatedDate, Long userId, int page, int size);
    Integer findScheduleCount(String updatedDate, Long userId);
    Optional<ScheduleResponseDto> findSchedule(Long scheduleId);
    Optional<ScheduleResponseDto> checkPasswordMatch(Long scheduleId, String password);
    boolean updateSchedule(Long scheduleId, String content, String title, String authorName, String password);
    boolean deleteSchedule(Long scheduleId, String password);
}
