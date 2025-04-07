package com.example.scheduleProject.domain.schedule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.scheduleProject.domain.schedule.dto.request.FindScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import com.example.scheduleProject.domain.schedule.repository.ScheduleRepository;
import com.example.scheduleProject.domain.user.entity.Users;
import com.example.scheduleProject.domain.user.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Tag("schedule")
@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
    @InjectMocks
    private ScheduleServiceImpl scheduleService;
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private UserRepository userRepository;

    @Tag("save")
    @Test
    @DisplayName("일정을 저장하면 scheduleId를 반환한다.")
    void saveSchedule_shouldReturnScheduleId() {
        // given
        Long userId = 1L;
        SaveScheduleRequestDto requestDto = new SaveScheduleRequestDto("title", "content", "password");

        Schedule savedSchedule = Schedule.builder()
            .scheduleId(10L)
            .title("title")
            .content("content")
            .password("password")
            .userId(userId)
            .build();

        given(userRepository.findById(userId)).willReturn(Optional.of(new Users("thals", "yjk8871@naver.com", "1234")));
        given(scheduleRepository.save(any(Schedule.class))).willReturn(savedSchedule);

        // when
        SaveScheduleResponseDto response = scheduleService.saveSchedule(userId, requestDto);

        // then
        assertEquals(10L, response.scheduleId());
        verify(scheduleRepository).save(any(Schedule.class));
    }

    @Tag("find")
    @Nested
    @DisplayName("FindSchedule 테스트")
    class FindScheduleTest {

        @Tag("find-one")
        @Test
        @DisplayName("일정 단건 조회 테스트")
        void findSchedule_shouldReturnOneSchedule() {
            // given
            Long scheduleId = 1L;
            ScheduleResponseDto responseDto = new ScheduleResponseDto(scheduleId, "title", "content", 7L, LocalDateTime.now(), LocalDateTime.now(), "authorName");
            given(scheduleRepository.findScheduleByScheduleId(scheduleId)).willReturn(Optional.of(responseDto));

            // when
            ScheduleResponseDto result = scheduleService.findSchedule(scheduleId);

            // then
            assertEquals(scheduleId, result.scheduleId());
        }

        @Tag("find-all")
        @Test
        @DisplayName("페이징된 일정 조회 테스트")
        void findAllSchedules_shouldReturnPagedSchedules() {
            // given
            FindScheduleRequestDto requestDto = new FindScheduleRequestDto("2025-04-07", "thals");

            Pageable pageable = PageRequest.of(0, 10); // 첫 페이지, 페이지당 10개

            List<ScheduleResponseDto> scheduleList = List.of(
                new ScheduleResponseDto(1L, "title1", "content1", 7L, requestDto.getStartOfDay(), requestDto.getEndOfDay(), "thals"),
                new ScheduleResponseDto(2L, "title2", "content2", 4L, requestDto.getStartOfDay(), requestDto.getEndOfDay(), "thals")
            );
            Page<ScheduleResponseDto> mockPage = new PageImpl<>(scheduleList, pageable, scheduleList.size());

            // mocking
            given(scheduleRepository.findSchedules("thals", requestDto.getStartOfDay(), requestDto.getEndOfDay(), pageable)).willReturn(mockPage);

            // when
            Page<ScheduleResponseDto> result = scheduleService.findAllSchedules(requestDto, pageable);

            // then
            assertEquals(2, result.getContent().size());
            assertEquals("title1", result.getContent().get(0).title());
            assertEquals("title2", result.getContent().get(1).title());
            verify(scheduleRepository).findSchedules("thals", requestDto.getStartOfDay(), requestDto.getEndOfDay(), pageable);
        }
    }

}