package com.example.scheduleProject.domain.schedule.service;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto);
    List<ScheduleResponseDto> findAllSchedules(FindScheduleRequestDto requestDto);
    ScheduleResponseDto findSchedule(Long scheduleId);
    ScheduleResponseDto updateSchedule(Long scheduleId, UpdateScheduleRequestDto requestDto);
    void deleteSchedule(Long scheduleId, DeleteScheduleRequestDto requestDto);
}
