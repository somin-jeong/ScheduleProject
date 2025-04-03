package com.example.scheduleProject.domain.schedule.service;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import com.example.scheduleProject.domain.schedule.repository.ScheduleRepository;
import com.example.scheduleProject.domain.user.repository.UserRepository;
import com.example.scheduleProject.global.exception.ScheduleException;
import com.example.scheduleProject.global.exception.UserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.scheduleProject.global.response.status.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public SaveScheduleResponseDto saveSchedule(Long userId, SaveScheduleRequestDto requestDto) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));

        Schedule schedule = Schedule.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .password(requestDto.password())
                .userId(userId).build();

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new SaveScheduleResponseDto(savedSchedule.getScheduleId());
    }

    @Override
    public Page<ScheduleResponseDto> findAllSchedules(FindScheduleRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.page(), requestDto.size());

        return scheduleRepository.findSchedules(requestDto.authorName(), requestDto.getStartOfDay(), requestDto.getEndOfDay(), pageable);
    }

    @Override
    public ScheduleResponseDto findSchedule(Long scheduleId) {
        return scheduleRepository.findScheduleByScheduleId(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));
    }

    @Override
    public void updateSchedule(Long scheduleId, UpdateScheduleRequestDto requestDto) {
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));
        scheduleRepository.findByScheduleIdAndPassword(scheduleId, requestDto.password())
                .orElseThrow(() -> new ScheduleException(NOT_PASSWORD_MATCH));

        boolean isUpdated = scheduleRepository.updateSchedule(scheduleId, requestDto.content(), requestDto.title(), requestDto.password());
        if (!isUpdated) {
            throw new ScheduleException(FAIL_SCHEDULE_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequestDto requestDto) {
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));
        scheduleRepository.findByScheduleIdAndPassword(scheduleId, requestDto.password())
                .orElseThrow(() -> new ScheduleException(NOT_PASSWORD_MATCH));

        Integer count = scheduleRepository.deleteByScheduleIdAndPassword(scheduleId, requestDto.password())
                .orElseThrow(() -> new ScheduleException(FAIL_SCHEDULE_DELETE_ERROR));

        if (count != 1) {
            throw new ScheduleException(FAIL_SCHEDULE_DELETE_ERROR);
        }
    }
}
