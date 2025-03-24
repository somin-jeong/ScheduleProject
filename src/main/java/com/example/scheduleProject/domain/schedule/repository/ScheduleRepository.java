package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    SaveScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedules(String updatedDate, String authorName);
    ScheduleResponseDto findSchedule(Long scheduleId);
    boolean checkPasswordMatch(Long scheduleId, String password);
    boolean updateSchedule(Long scheduleId, String content, String title, String authorName, String password);

}
