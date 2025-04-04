package com.example.scheduleProject.domain.user.repository;

import com.example.scheduleProject.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Integer> deleteByUserId(Long userId);

    Optional<Users> findByEmail(String email);
}
