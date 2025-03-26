package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.PageResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class JdbcScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public SaveScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule")
                .usingGeneratedKeyColumns("schedule_id")
                .usingColumns("title", "content", "password", "author_name", "user_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("content", schedule.getContent());
        parameters.put("password", schedule.getPassword());
        parameters.put("author_name", schedule.getAuthorName());
        parameters.put("user_id", schedule.getUserId());

        Number number = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SaveScheduleResponseDto(number.longValue());
    }

    @Override
    public PageResponseDto<ScheduleResponseDto> findAllSchedules(String updatedDate, Long userId, int page, int size) {
        String sql = "SELECT schedule_id, title, content, author_name, password " +
                "FROM schedule " +
                "WHERE (? IS NULL OR DATE(updated_at) = ?) " +
                "AND (? IS NULL OR user_id = ?) " +
                "ORDER BY updated_at DESC " +
                "LIMIT ? OFFSET ?";

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, updatedDate);
            ps.setString(2, updatedDate);
            if (userId != null) {
                ps.setLong(3, userId);
                ps.setLong(4, userId);
            } else {
                ps.setNull(3, Types.BIGINT);
                ps.setNull(4, Types.BIGINT);
            }
            ps.setInt(5, size);
            ps.setInt(6, page*size);
        };

        List<ScheduleResponseDto> scheduleResponseDtos = jdbcTemplate.query(sql, preparedStatementSetter, schedulesRowMapper());
        return new PageResponseDto<>(scheduleResponseDtos, page, size, findScheduleCount(updatedDate, userId));
    }

    @Override
    public Integer findScheduleCount(String updatedDate, Long userId) {
        String sql = "SELECT COUNT(*) " +
                "FROM schedule " +
                "WHERE (? IS NULL OR DATE(updated_at) = ?) " +
                "AND (? IS NULL OR user_id = ?) " +
                "ORDER BY updated_at DESC ";

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, updatedDate);
            ps.setString(2, updatedDate);
            if (userId != null) {
                ps.setLong(3, userId);
                ps.setLong(4, userId);
            } else {
                ps.setNull(3, Types.BIGINT);
                ps.setNull(4, Types.BIGINT);
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, rs -> {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        });
    }

    private RowMapper<ScheduleResponseDto> schedulesRowMapper() {
        return (rs, rowNum) -> new ScheduleResponseDto(
                rs.getLong("schedule_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("author_name"),
                rs.getString("password")
        );
    }

    @Override
    public Optional<ScheduleResponseDto> findSchedule(Long scheduleId) {
        String sql = "SELECT schedule_id, title, content, author_name, password " +
                "FROM schedule " +
                "WHERE schedule_id = ?";

        return jdbcTemplate.query(sql, getOptionalResultSetExtractor(), scheduleId);
    }

    private static ResultSetExtractor<Optional<ScheduleResponseDto>> getOptionalResultSetExtractor() {
        return rs -> {
            if (rs.next()) {
                return Optional.of(new ScheduleResponseDto(
                        rs.getLong("schedule_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author_name"),
                        rs.getString("password")
                ));
            } else {
                return Optional.empty();
            }
        };
    }

    @Override
    public Optional<ScheduleResponseDto> checkPasswordMatch(Long scheduleId, String password) {
        String sql = "SELECT * " +
                "FROM schedule s " +
                "WHERE s.schedule_id = ? AND s.password = ?";

        return jdbcTemplate.query(sql, getOptionalResultSetExtractor(), scheduleId, password);
    }

    @Override
    public boolean updateSchedule(Long scheduleId, String content, String title, String authorName, String password) {
        StringBuilder sql = new StringBuilder("UPDATE schedule SET ");
        List<Object> params = new ArrayList<>();

        if (title != null) {
            sql.append("title = ?, ");
            params.add(title);
        }
        if (content != null) {
            sql.append("content = ?, ");
            params.add(content);
        }
        if (authorName != null) {
            sql.append("author_name = ? ");
            params.add(authorName);
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.append("WHERE schedule_id = ? AND password = ?");
        params.add(scheduleId);
        params.add(password);

        int updated = jdbcTemplate.update(sql.toString(), params.toArray());
        return updated > 0;
    }

    @Override
    public void deleteSchedule(Long scheduleId, String password) {
        String sql = "DELETE FROM schedule WHERE schedule_id = ? AND password = ?";

        jdbcTemplate.update(sql, scheduleId, password);
    }
}
