package com.example.scheduleProject.domain.schedule.controller;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.PageResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.service.ScheduleService;
import com.example.scheduleProject.global.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    private BaseResponse<SaveScheduleResponseDto> saveSchedule(@RequestBody @Valid SaveScheduleRequestDto requestDto) {
        SaveScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return new BaseResponse<>(responseDto);
    }

    @GetMapping("/schedules")
    private BaseResponse<PageResponseDto<ScheduleResponseDto>> findAllSchedules(@ModelAttribute @Valid FindScheduleRequestDto requestDto) {
        PageResponseDto<ScheduleResponseDto> schedules = scheduleService.findAllSchedules(requestDto);
        return new BaseResponse<>(schedules);
    }

    @GetMapping("/schedules/{scheduleId}")
    private BaseResponse<ScheduleResponseDto> findSchedule(@PathVariable @Valid Long scheduleId) {
        ScheduleResponseDto schedule = scheduleService.findSchedule(scheduleId);
        return new BaseResponse<>(schedule);
    }

    @PutMapping("/schedules/{scheduleId}")
    private BaseResponse<String> updateSchedule(@PathVariable @Valid Long scheduleId, @RequestBody @Valid UpdateScheduleRequestDto requestDto) {
        String result = scheduleService.updateSchedule(scheduleId, requestDto);
        return new BaseResponse<>(result);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    private BaseResponse<String> deleteSchedule(@PathVariable @Valid Long scheduleId, @RequestBody @Valid DeleteScheduleRequestDto requestDto) {
        String result = scheduleService.deleteSchedule(scheduleId, requestDto);
        return new BaseResponse<>(result);
    }
}
