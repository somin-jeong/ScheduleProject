package com.example.scheduleProject.domain.schedule.repository;

public interface CustomScheduleRepository {
    boolean updateSchedule(Long scheduleId, String content, String title, String password);
}
