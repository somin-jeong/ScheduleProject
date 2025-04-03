package com.example.scheduleProject.domain.comment.service;

import com.example.scheduleProject.domain.comment.dto.request.CommentRequestDto;
import com.example.scheduleProject.domain.comment.dto.response.CommentResponseDto;
import com.example.scheduleProject.domain.comment.dto.response.SaveCommentResponseDto;

import java.util.List;

public interface CommentService {
    SaveCommentResponseDto saveComment(Long userId, Long scheduleId, CommentRequestDto requestDto);

    List<CommentResponseDto> findComment(Long scheduleId);

    void updateComment(Long userId, Long commentId, CommentRequestDto requestDto);

    void deleteComment(Long userId, Long commentId);
}
