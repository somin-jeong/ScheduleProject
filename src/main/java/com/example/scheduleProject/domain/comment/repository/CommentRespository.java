package com.example.scheduleProject.domain.comment.repository;

import com.example.scheduleProject.domain.comment.dto.response.CommentResponseDto;
import com.example.scheduleProject.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRespository extends JpaRepository<Comment, Long> {
    @Query("""
    SELECT new com.example.scheduleProject.domain.comment.dto.response.CommentResponseDto
        (c.commentId, c.content, u.name, c.createdAt, c.updatedAt)
    FROM Comment c LEFT JOIN Users u ON c.userId = u.userId
    WHERE c.scheduleId = :scheduleId
    """)
    List<CommentResponseDto> findComents(Long scheduleId);

    @Modifying
    @Query("""
    UPDATE Comment c
    SET c.content = COALESCE(:comment, c.content)
    WHERE c.commentId = :commentId
    """)
    Optional<Integer> updateComment(String comment, Long commentId);

    Optional<Integer> deleteByCommentId(Long commentId);
}
