package com.example.scheduleProject.domain.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsByUserId(Long userId) {
        String sql = "SELECT COUNT(*) " +
                "FROM users " +
                "WHERE user_id = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);

        return count != 0;
    }
}
