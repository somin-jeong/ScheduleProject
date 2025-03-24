package com.example.scheduleProject.domain.schedule.repository;

import com.example.scheduleProject.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.scheduleProject.domain.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JdbcScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public SaveScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule")
                .usingGeneratedKeyColumns("schedule_id")
                .usingColumns("title", "content", "password", "user_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("content", schedule.getContent());
        parameters.put("password", schedule.getPassword());
        parameters.put("user_id", schedule.getUserId());

        Number number = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SaveScheduleResponseDto(number.longValue());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String updatedDate, String authorName) {
        String sql = "SELECT s.schedule_id, s.title, s.content, s.password, u.name " +
                "FROM schedule s JOIN users u ON s.user_id = u.user_id " +
                "WHERE (? IS NULL OR DATE(s.updated_at) = ?) " +
                "AND (? IS NULL OR u.name = ?) " +
                "ORDER BY s.updated_at DESC";

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, updatedDate);
            ps.setString(2, updatedDate);
            ps.setString(3, authorName);
            ps.setString(4, authorName);
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, schedulesRowMapper());
    }

    private RowMapper<ScheduleResponseDto> schedulesRowMapper() {
        return (rs, rowNum) -> new ScheduleResponseDto(
                rs.getLong("schedule_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("password"),
                rs.getString("name")
        );
    }

    @Override
    public ScheduleResponseDto findSchedule(Long scheduleId) {
        String sql = "SELECT s.schedule_id, s.title, s.content, s.password, u.name " +
                "FROM schedule s JOIN users u ON s.user_id = u.user_id " +
                "WHERE s.schedule_id = ?";

        return jdbcTemplate.queryForObject(sql, schedulesRowMapper(), scheduleId);
    }

    @Override
    public boolean checkPasswordMatch(Long scheduleId, String password) {
        String sql = "SELECT COUNT(*) " +
                "FROM schedule s " +
                "WHERE s.schedule_id = ? AND s.password = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, scheduleId, password);

        return count != 0;
    }

    @Override
    public boolean updateSchedule(Long scheduleId, String content, String title, String authorName, String password) {
        String sql = "UPDATE schedule SET title = ?, content = ?, authorName = ?" +
                "WHERE schedule_id = ? AND password = ?";

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, authorName);
            ps.setLong(4, scheduleId);
            ps.setString(5, password);
        };

        int updated = jdbcTemplate.update(sql, preparedStatementSetter);
        return updated > 0;
    }
}
