package com.example.scheduleProject.domain.schedule.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long scheduleId;
    private String title;
    private String content;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    @Builder
    public Schedule(String title, String content, String password, Long userId) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.userId = userId;
    }
}
