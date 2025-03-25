package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    SaveScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedules(String updatedDate, Long userId);
    Optional<ScheduleResponseDto> findSchedule(Long scheduleId);
    Optional<ScheduleResponseDto> checkPasswordMatch(Long scheduleId, String password);
    boolean updateSchedule(Long scheduleId, String content, String title, String authorName, String password);
    void deleteSchedule(Long scheduleId, String password);
}
