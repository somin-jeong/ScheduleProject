package com.example.scheduleProject.domain.schedule.controller;

import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    private ResponseEntity<SaveScheduleResponseDto> saveSchedule(@RequestBody SaveScheduleRequestDto requestDto) {
        SaveScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/schedules")
    private ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(@ModelAttribute FindScheduleRequestDto requestDto) {
        List<ScheduleResponseDto> schedules = scheduleService.findAllSchedules(requestDto);
        return new ResponseEntity<>(schedules, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/schedules/{scheduleId}")
    private ResponseEntity<ScheduleResponseDto> findSchedule(@PathVariable Long scheduleId) {
        ScheduleResponseDto schedule = scheduleService.findSchedule(scheduleId);
        return new ResponseEntity<>(schedule, HttpStatusCode.valueOf(200));
    }

}
