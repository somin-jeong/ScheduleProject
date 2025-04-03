package com.example.scheduleProject.domain.user.repository;

import com.example.scheduleProject.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Modifying
    @Query("""
    UPDATE Users u
    SET u.name = COALESCE(:name, u.name),
        u.email = COALESCE(:email, u.email)
    WHERE u.userId = :userId
    """)
    Optional<Integer> updateUser(Long userId, String name, String email);

    Optional<Integer> deleteByUserId(Long userId);

    Optional<Users> findByEmail(String email);
}
