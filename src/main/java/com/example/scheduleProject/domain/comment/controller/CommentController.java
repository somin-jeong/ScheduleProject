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

    /**
     * 댓글 생성
     *
     * @param userId 유저Id
     * @param scheduleId 일정Id
     * @param requestDto (댓글 내용)
     * @return 생성된 댓글의 id를 포함한 응답 객체
     */
    @PostMapping("/schedules/{scheduleId}/comments")
    private BaseResponse<SaveCommentResponseDto> saveComment(
            @SessionAttribute(name = SESSION_NAME, required = false) Long userId,
            @PathVariable @Valid Long scheduleId,
            @RequestBody @Valid CommentRequestDto requestDto
    ) {
        SaveCommentResponseDto response = commentService.saveComment(userId, scheduleId, requestDto);
        return new BaseResponse<>(response);
    }

    /**
     * 댓글 조회
     *
     * @param scheduleId 일정Id
     * @return 댓글(댓글 ID, 내용, 작성자명, 작성일, 수정일) 리스트를 포함한 응답 객체
     */
    @GetMapping("/schedules/{scheduleId}/comments")
    private BaseResponse<List<CommentResponseDto>> findComment(@PathVariable @Valid Long scheduleId) {
        List<CommentResponseDto> comments = commentService.findComment(scheduleId);
        return new BaseResponse<>(comments);
    }

    /**
     * 댓글 수정
     *
     * @param userId 유저Id
     * @param commentId 댓글Id
     * @param requestDto (수정할 댓글 내용)
     * @return 수정 완료 메시지
     */
    @PutMapping("/comments/{commentId}")
    private BaseResponse<String> updateComment(
            @SessionAttribute(name = SESSION_NAME, required = false) Long userId,
            @PathVariable @Valid Long commentId,
            @RequestBody @Valid CommentRequestDto requestDto
    ) {
        commentService.updateComment(userId, commentId, requestDto);
        return new BaseResponse<>("수정 완료했습니다.");
    }

    /**
     * 댓글 삭제
     *
     * @param userId 유저Id
     * @param commentId 댓글Id
     * @return 삭제 완료 메시지
     */
    @DeleteMapping("/comments/{commentId}")
    private BaseResponse<String> deleteComment(
            @SessionAttribute(name = SESSION_NAME, required = false) Long userId,
            @PathVariable @Valid Long commentId
    ) {
        commentService.deleteComment(userId, commentId);
        return new BaseResponse<>("삭제 완료했습니다.");
    }
}
