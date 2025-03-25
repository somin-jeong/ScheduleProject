package com.example.scheduleProject.domain.user.repository;

import com.example.scheduleProject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByUserId(Long userId) {
        String sql = "SELECT * " +
                "FROM users " +
                "WHERE user_id = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, getUserRowMapper(), userId);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static RowMapper<User> getUserRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getLong("user_id"),
                rs.getString("email")
        );
    }
}
