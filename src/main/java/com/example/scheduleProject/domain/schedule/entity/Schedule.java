package com.example.scheduleProject.domain.schedule.entity;

import com.example.scheduleProject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public Schedule(String title, String content, String password, Long userId) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.userId = userId;
    }

    public void updateSchedule(String title, String content, String password) {
        if (title != null) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
        if (password != null) {
            this.password = password;
        }
    }
}
