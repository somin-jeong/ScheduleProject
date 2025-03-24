package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;

public interface ScheduleRepository {
    SaveScheduleResponseDto saveSchedule(Schedule schedule);
}
