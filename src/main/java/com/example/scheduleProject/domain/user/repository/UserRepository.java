package com.example.scheduleProject.domain.user.repository;

import com.example.scheduleProject.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserId(Long userId);
}
