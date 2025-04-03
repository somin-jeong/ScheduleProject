package com.example.scheduleProject.domain.schedule.controller;

import com.example.scheduleProject.domain.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.service.ScheduleService;
import com.example.scheduleProject.global.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    public static final String SESSION_NAME = "loginUser";
    private final ScheduleService scheduleService;

    /**
     * 일정 생성
     *
     * @param userId 사용자 id
     * @param requestDto (제목, 할일, 비밀번호)
     * @return 생성된 일정의 id를 포함한 응답 객체
     */
    @PostMapping("/schedules")
    private BaseResponse<SaveScheduleResponseDto> saveSchedule(
            @SessionAttribute(name = SESSION_NAME, required = false) Long userId,
            @RequestBody @Valid SaveScheduleRequestDto requestDto
    ) {
        SaveScheduleResponseDto responseDto = scheduleService.saveSchedule(userId, requestDto);
        return new BaseResponse<>(responseDto);
    }

    /**
     * 필터링 조건에 맞는 일정을 페이징하여 조회
     *
     * @param requestDto (수정일, 작성자명)
     * @param pageable (페이지, 크기)
     * @return 페이징된 일정(일정 ID, 제목, 할일, 댓글수, 작성자명, 작성일, 수정일) 리스트를 포함한 응답 객체
     */
    @GetMapping("/schedules")
    private BaseResponse<Page<ScheduleResponseDto>> findAllSchedules(
            @ModelAttribute @Valid FindScheduleRequestDto requestDto,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ScheduleResponseDto> schedules = scheduleService.findAllSchedules(requestDto, pageable);
        return new BaseResponse<>(schedules);
    }

    /**
     * 특정 일정 ID에 해당하는 일정 조회
     *
     * @param scheduleId 조회할 일정 ID
     * @return 일정(일정 ID, 제목, 할일, 댓글수, 작성자명, 작성일, 수정일) 정보를 포함한 응답 객체
     */
    @GetMapping("/schedules/{scheduleId}")
    private BaseResponse<ScheduleResponseDto> findSchedule(@PathVariable @Valid Long scheduleId) {
        ScheduleResponseDto schedule = scheduleService.findSchedule(scheduleId);
        return new BaseResponse<>(schedule);
    }

    /**
     * 특정 일정 ID에 해당하는 일정 수정
     *
     * @param scheduleId 수정할 일정 ID
     * @param requestDto (title, content, password)
     * @return 수정 성공 메시지
     */
    @PutMapping("/schedules/{scheduleId}")
    private BaseResponse<String> updateSchedule(@PathVariable @Valid Long scheduleId, @RequestBody @Valid UpdateScheduleRequestDto requestDto) {
        scheduleService.updateSchedule(scheduleId, requestDto);
        return new BaseResponse<>("수정 완료했습니다.");
    }

    /**
     * 특정 일정 ID에 해당하는 일정 삭제
     *
     * @param scheduleId 삭제할 일정 ID
     * @param requestDto (password)
     * @return 삭제 성공 메시지
     */
    @DeleteMapping("/schedules/{scheduleId}")
    private BaseResponse<String> deleteSchedule(@PathVariable @Valid Long scheduleId, @RequestBody @Valid DeleteScheduleRequestDto requestDto) {
        scheduleService.deleteSchedule(scheduleId, requestDto);
        return new BaseResponse<>("삭제 완료했습니다.");
    }
}
