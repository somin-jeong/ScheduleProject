package com.example.scheduleProject.domain.user.entity;

import lombok.Builder;

import java.time.LocalDateTime;

public class User {
    private Long userId;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public User(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
