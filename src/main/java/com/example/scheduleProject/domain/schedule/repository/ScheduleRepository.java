package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("""
    SELECT new com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto
        (s.scheduleId, s.title, s.content, count(c.commentId), s.updatedAt, s.createdAt, u.name)
    FROM Schedule s
        LEFT JOIN Users u ON s.userId = u.userId
        LEFT JOIN Comment c ON s.scheduleId = c.scheduleId
    WHERE (:authorName IS NULL OR u.name LIKE %:authorName%)
        AND (:startOfDay IS NULL OR s.updatedAt BETWEEN :startOfDay AND :endOfDay)
    GROUP BY s.scheduleId, s.title, s.content, s.updatedAt, s.createdAt, u.name
    ORDER BY s.updatedAt desc
    """)
    Page<ScheduleResponseDto> findSchedules(String authorName, LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable);

    @Query("""
    SELECT new com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto
        (s.scheduleId, s.title, s.content, count(c.commentId), s.updatedAt, s.createdAt, u.name)
    FROM Schedule s
        LEFT JOIN Users u ON s.userId = u.userId
        LEFT JOIN Comment c ON s.scheduleId = c.scheduleId
    WHERE s.scheduleId = :scheduleId
    GROUP BY s.scheduleId, s.title, s.content, s.updatedAt, s.createdAt, u.name
    """)
    Optional<ScheduleResponseDto> findScheduleByScheduleId(Long scheduleId);

    Optional<Schedule> findByScheduleIdAndPassword(Long scheduleId, String password);

    @Modifying
    @Query("""
    UPDATE Schedule s
    SET s.content = COALESCE(:content, s.content),
        s.title = COALESCE(:title, s.title)
    WHERE s.scheduleId = :scheduleId AND s.password = :password
    """)
    Optional<Integer> updateSchedule(Long scheduleId, String content, String title, String password);

    Optional<Integer> deleteByScheduleIdAndPassword(Long scheduleId, String password);
}
