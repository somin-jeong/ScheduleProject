package com.example.scheduleProject.domain.schedule.controller;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.PageResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    private ResponseEntity<SaveScheduleResponseDto> saveSchedule(@RequestBody @Valid SaveScheduleRequestDto requestDto) {
        SaveScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/schedules")
    private ResponseEntity<PageResponseDto<ScheduleResponseDto>> findAllSchedules(@ModelAttribute @Valid FindScheduleRequestDto requestDto) {
        PageResponseDto<ScheduleResponseDto> schedules = scheduleService.findAllSchedules(requestDto);
        return new ResponseEntity<>(schedules, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/schedules/{scheduleId}")
    private ResponseEntity<ScheduleResponseDto> findSchedule(@PathVariable @Valid Long scheduleId) {
        ScheduleResponseDto schedule = scheduleService.findSchedule(scheduleId);
        return new ResponseEntity<>(schedule, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/schedules/{scheduleId}")
    private ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable @Valid Long scheduleId, @RequestBody @Valid UpdateScheduleRequestDto requestDto) {
        ScheduleResponseDto schedule = scheduleService.updateSchedule(scheduleId, requestDto);
        return new ResponseEntity<>(schedule, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    private ResponseEntity<String> deleteSchedule(@PathVariable @Valid Long scheduleId, @RequestBody @Valid DeleteScheduleRequestDto requestDto) {
        scheduleService.deleteSchedule(scheduleId, requestDto);
        return new ResponseEntity<>("삭제 완료", HttpStatusCode.valueOf(200));
    }
}
