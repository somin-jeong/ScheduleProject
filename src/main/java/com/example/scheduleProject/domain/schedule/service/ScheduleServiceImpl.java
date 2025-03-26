package com.example.scheduleProject.domain.schedule.service;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.PageResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import com.example.scheduleProject.domain.schedule.repository.ScheduleRepository;
import com.example.scheduleProject.domain.user.repository.UserRepository;
import com.example.scheduleProject.global.exception.ScheduleException;
import com.example.scheduleProject.global.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.scheduleProject.global.response.status.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto) {
        userRepository.findByUserId(requestDto.userId())
                .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));

        Schedule schedule = Schedule.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .password(requestDto.password())
                .authorName(requestDto.authorName())
                .userId(requestDto.userId()).build();

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public PageResponseDto<ScheduleResponseDto> findAllSchedules(FindScheduleRequestDto requestDto) {
        if (requestDto.userId() != null) {
            userRepository.findByUserId(requestDto.userId())
                    .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));
        }

        return scheduleRepository.findAllSchedules(requestDto.updatedDate(), requestDto.userId(), requestDto.page(), requestDto.size());
    }

    @Override
    public ScheduleResponseDto findSchedule(Long scheduleId) {
        return scheduleRepository.findSchedule(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));
    }

    @Override
    public String updateSchedule(Long scheduleId, UpdateScheduleRequestDto requestDto) {
        scheduleRepository.findSchedule(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));
        scheduleRepository.checkPasswordMatch(scheduleId, requestDto.password())
                .orElseThrow(() -> new ScheduleException(NOT_PASSWORD_MATCH));

        boolean isUpdated = scheduleRepository.updateSchedule(scheduleId, requestDto.content(), requestDto.title(), requestDto.authorName(), requestDto.password());
        if (isUpdated) {
            return "수정 완료되었습니다.";
        }
        return "수정이 완료되지 않았습니다.";
    }

    @Override
    public String deleteSchedule(Long scheduleId, DeleteScheduleRequestDto requestDto) {
        scheduleRepository.findSchedule(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));
        scheduleRepository.checkPasswordMatch(scheduleId, requestDto.password())
                .orElseThrow(() -> new ScheduleException(NOT_PASSWORD_MATCH));

        boolean isDeleted = scheduleRepository.deleteSchedule(scheduleId, requestDto.password());
        if (isDeleted) {
            return "삭제 완료되었습니다.";
        }
        return "삭제가 완료되지 않았습니다.";
    }
}
