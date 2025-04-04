package com.example.scheduleProject.domain.comment.service;

import com.example.scheduleProject.domain.comment.dto.request.CommentRequestDto;
import com.example.scheduleProject.domain.comment.dto.response.CommentResponseDto;
import com.example.scheduleProject.domain.comment.dto.response.SaveCommentResponseDto;
import com.example.scheduleProject.domain.comment.entity.Comment;
import com.example.scheduleProject.domain.comment.repository.CommentRespository;
import com.example.scheduleProject.domain.schedule.repository.ScheduleRepository;
import com.example.scheduleProject.domain.user.repository.UserRepository;
import com.example.scheduleProject.global.exception.CommentException;
import com.example.scheduleProject.global.exception.ScheduleException;
import com.example.scheduleProject.global.exception.UserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.scheduleProject.global.response.status.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRespository commentRespository;

    @Override
    public SaveCommentResponseDto saveComment(Long userId, Long scheduleId, CommentRequestDto requestDto) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));

        Comment comment = Comment.builder()
                .content(requestDto.comment())
                .userId(userId)
                .scheduleId(scheduleId)
                .build();

        Comment savedComment = commentRespository.save(comment);
        return new SaveCommentResponseDto(savedComment.getCommentId());
    }

    @Override
    public List<CommentResponseDto> findComment(Long scheduleId) {
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_SCHEDULE_ERROR));

        return commentRespository.findComents(scheduleId);
    }

    @Override
    public void updateComment(Long userId, Long commentId, CommentRequestDto requestDto) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));
        Comment comment = commentRespository.findById(commentId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_COMMENT_ERROR));

        // 댓글 작성한 사람이 현재 로그인한 유저인지 확인
        if (!Objects.equals(comment.getUserId(), userId)) {
            throw new CommentException(NOT_ALLOW_UPDATE_COMMENT_ERROR);
        }

        comment.updateComment(requestDto.comment());
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));
        Comment comment = commentRespository.findById(commentId)
                .orElseThrow(() -> new ScheduleException(NOT_EXIST_COMMENT_ERROR));

        // 댓글 작성한 사람이 현재 로그인한 유저인지 확인
        if (!Objects.equals(comment.getUserId(), userId)) {
            throw new CommentException(NOT_ALLOW_DELETE_COMMENT_ERROR);
        }

        Integer count = commentRespository.deleteByCommentId(commentId)
                .orElseThrow(() -> new CommentException(FAIL_COMMENT_DELETE_ERROR));

        if (count != 1) {
            throw new CommentException(FAIL_COMMENT_DELETE_ERROR);
        }
    }
}
