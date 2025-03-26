package com.example.scheduleProject.domain.schedule.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponseDto<T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean isLast;

    public PageResponseDto(List<T> content, int page, int size, long totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
        this.isLast = (page + 1) >= totalPages;
    }
}