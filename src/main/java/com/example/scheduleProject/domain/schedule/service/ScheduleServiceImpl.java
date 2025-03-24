package com.example.scheduleProject.domain.schedule.service;

import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import com.example.scheduleProject.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto) {
        Schedule schedule = Schedule.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .password(requestDto.password())
                .userId(requestDto.userId()).build();

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(FindScheduleRequestDto requestDto) {
        return scheduleRepository.findAllSchedules(requestDto.updatedDate(), requestDto.authorName());
    }

    @Override
    public ScheduleResponseDto findSchedule(Long scheduleId) {
        return scheduleRepository.findSchedule(scheduleId);
    }
}
