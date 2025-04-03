package com.example.scheduleProject.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDto(
        @NotBlank String comment
) { }
