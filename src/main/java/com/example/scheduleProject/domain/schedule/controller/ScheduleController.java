package com.example.scheduleProject.domain.schedule.controller;

import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.service.ScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleServiceImpl scheduleServiceImpl;

    @PostMapping("/schedules")
    private ResponseEntity<SaveScheduleResponseDto> saveSchedule(@RequestBody SaveScheduleRequestDto requestDto) {
        SaveScheduleResponseDto responseDto = scheduleServiceImpl.saveSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(200));
    }
}
