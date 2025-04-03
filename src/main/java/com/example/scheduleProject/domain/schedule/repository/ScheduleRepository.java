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
        (s.scheduleId, s.title, s.content, u.name, s.password)
    FROM Schedule s LEFT JOIN Users u ON s.userId = u.userId
    WHERE (:authorName IS NULL OR u.name LIKE %:authorName%)
        AND (:startOfDay IS NULL OR s.updatedAt BETWEEN :startOfDay AND :endOfDay)
    """)
    Page<ScheduleResponseDto> findSchedules(String authorName, LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable);

    @Query("""
    SELECT new com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto
        (s.scheduleId, s.title, s.content, u.name, s.password)
    FROM Schedule s LEFT JOIN Users u ON s.userId = u.userId
    WHERE s.scheduleId = :scheduleId
    """)
    Optional<ScheduleResponseDto> findScheduleByScheduleId(Long scheduleId);

    Optional<Schedule> findByScheduleIdAndPassword(Long scheduleId, String password);
    Optional<Integer> deleteByScheduleIdAndPassword(Long scheduleId, String password);
}
