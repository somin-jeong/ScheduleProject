package com.example.scheduleProject.domain.schedule.service;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
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
                .authorName(requestDto.authorName())
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

    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, UpdateScheduleRequestDto requestDto) {
        if (!scheduleRepository.checkPasswordMatch(scheduleId, requestDto.password())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        boolean isUpdated = scheduleRepository.updateSchedule(scheduleId, requestDto.content(), requestDto.title(), requestDto.authorName(), requestDto.password());
        if (isUpdated) {
            return new ScheduleResponseDto(scheduleId, requestDto.title(), requestDto.content(), requestDto.authorName(), requestDto.password());
        }
        return null;
    }

    @Override
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequestDto requestDto) {
        if (!scheduleRepository.checkPasswordMatch(scheduleId, requestDto.password())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.deleteSchedule(scheduleId, requestDto.password());
    }
}
