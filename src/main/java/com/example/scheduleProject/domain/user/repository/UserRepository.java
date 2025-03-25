package com.example.scheduleProject.domain.user.repository;

public interface UserRepository {
    boolean existsByUserId(Long userId);
}
