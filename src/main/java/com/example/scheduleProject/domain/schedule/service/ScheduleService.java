package com.example.scheduleProject.domain.schedule.service;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    SaveScheduleResponseDto saveSchedule(Long userId, SaveScheduleRequestDto requestDto);
    Page<ScheduleResponseDto> findAllSchedules(FindScheduleRequestDto requestDto, Pageable pageable);
    ScheduleResponseDto findSchedule(Long scheduleId);
    void updateSchedule(Long userId, Long scheduleId, UpdateScheduleRequestDto requestDto);
    void deleteSchedule(Long userId, Long scheduleId, DeleteScheduleRequestDto requestDto);
}
