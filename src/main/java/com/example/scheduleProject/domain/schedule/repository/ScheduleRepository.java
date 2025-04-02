package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, CustomScheduleRepository {
    @Query("""
    SELECT new com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto
        (s.scheduleId, s.title, s.content, s.authorName, s.password)
    FROM Schedule s
    WHERE (:userId IS NULL OR s.userId = :userId)
        AND (:startOfDay IS NULL OR s.updatedAt BETWEEN :startOfDay AND :endOfDay)
    """)
    Page<ScheduleResponseDto> findSchedules(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable);
    Optional<Schedule> findByScheduleIdAndPassword(Long scheduleId, String password);
    Optional<Integer> deleteByScheduleIdAndPassword(Long scheduleId, String password);
}
