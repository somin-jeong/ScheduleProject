package com.example.scheduleProject.domain.schedule.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements CustomScheduleRepository {
    private final EntityManager em;

    @Override
    public boolean updateSchedule(Long scheduleId, String content, String title, String password) {
        StringBuilder jpql = new StringBuilder("UPDATE Schedule s SET ");
        Map<String, Object> params = new HashMap<>();
        List<String> updates = new ArrayList<>();

        if (title != null) {
            updates.add("s.title = :title");
            params.put("title", title);
        }
        if (content != null) {
            updates.add("s.content = :content");
            params.put("content", content);
        }

        if (updates.isEmpty()) return false;

        jpql.append(String.join(", ", updates));
        jpql.append(" WHERE s.scheduleId = :scheduleId AND s.password = :password");

        params.put("scheduleId", scheduleId);
        params.put("password", password);

        Query query = em.createQuery(jpql.toString());
        params.forEach(query::setParameter);

        int updatedCount = query.executeUpdate();
        return updatedCount > 0;
    }
}
