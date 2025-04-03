package com.example.scheduleProject.domain.comment.controller;

import com.example.scheduleProject.domain.comment.dto.request.CommentRequestDto;
import com.example.scheduleProject.domain.comment.dto.response.CommentResponseDto;
import com.example.scheduleProject.domain.comment.dto.response.SaveCommentResponseDto;
import com.example.scheduleProject.domain.comment.service.CommentService;
import com.example.scheduleProject.global.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    public static final String SESSION_NAME = "loginUser";
    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    private BaseResponse<SaveCommentResponseDto> saveComment(
            @SessionAttribute(name = SESSION_NAME, required = false) Long userId,
            @PathVariable @Valid Long scheduleId,
            @RequestBody @Valid CommentRequestDto requestDto
    ) {
        SaveCommentResponseDto response = commentService.saveComment(userId, scheduleId, requestDto);
        return new BaseResponse<>(response);
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    private BaseResponse<List<CommentResponseDto>> findComment(@PathVariable @Valid Long scheduleId) {
        List<CommentResponseDto> comments = commentService.findComment(scheduleId);
        return new BaseResponse<>(comments);
    }

    @PutMapping("/comments/{commentId}")
    private BaseResponse<String> updateComment(
            @SessionAttribute(name = SESSION_NAME, required = false) Long userId,
            @PathVariable @Valid Long commentId,
            @RequestBody @Valid CommentRequestDto requestDto
    ) {
        commentService.updateComment(userId, commentId, requestDto);
        return new BaseResponse<>("삭제 완료했습니다.");
    }

    @DeleteMapping("/comments/{commentId}")
    private BaseResponse<String> deleteComment(
            @SessionAttribute(name = SESSION_NAME, required = false) Long userId,
            @PathVariable @Valid Long commentId
    ) {
        commentService.deleteComment(userId, commentId);
        return new BaseResponse<>("삭제 완료했습니다.");
    }
}
