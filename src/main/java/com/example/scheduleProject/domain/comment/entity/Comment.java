package com.example.scheduleProject.domain.comment.entity;

import com.example.scheduleProject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long scheduleId;

    @Builder
    public Comment(String content, Long userId, Long scheduleId) {
        this.content = content;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }

    public void updateComment(String comment) {
        if (comment != null) {
            this.content = comment;
        }
    }
}
