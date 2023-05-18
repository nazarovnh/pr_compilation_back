package com.professor_compilation.core.repository.topic.access;

import com.professor_compilation.core.entity.topic.access.rdbms.TopicAccessRdbms;
import com.professor_compilation.core.model.exception.NoSuchElementFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.Optional;

import static com.professor_compilation.constants.Constants.*;
import static com.professor_compilation.utils.rowmapper.EntityRowMapper.getEntityRowMapper;

public class TopicAccessRepository implements ITopicAccessRepository<TopicAccessRdbms, String> {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final String stampUserId = "userId";
    private final String stampTopicId = "topicId";
    private final String stampMaxSumScore = "maxSumScore";
    private final String stampAccessByScore = "accessByScore";
    private final String stampAccessFromTeacher = "accessFromTeacher";

    public TopicAccessRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Optional<TopicAccessRdbms> findById(final String userId, final String topicId) {
        try {
            String sql = String.format("SELECT * FROM topic_access WHERE user_id = :%s AND topic_id = :%s", stampUserId, stampTopicId);
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue(stampUserId, userId)
                    .addValue(stampTopicId, topicId);
            TopicAccessRdbms topicAccessRdbms = jdbcOperations.queryForObject(sql, params, getEntityRowMapper(TopicAccessRdbms.class));
            return Optional.of(topicAccessRdbms);
        } catch (
                EmptyResultDataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TOPIC_ACCESS, "userId = " + userId + "topicId = " + topicId));
        }
    }

    @Override
    public void updateMaxScore(final String userId, final String topicId, final int newMaxScore) {
        String sql = String.format("UPDATE topic_access SET max_sum_score = :%s WHERE user_id = :%s AND topic_id = :%s",
                stampMaxSumScore, stampUserId, stampTopicId);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(stampMaxSumScore, newMaxScore)
                .addValue(stampUserId, userId)
                .addValue(stampTopicId, topicId);
        try {
            jdbcOperations.update(sql, params);
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TOPIC_ACCESS, userId + topicId));
        }
    }

    @Override
    public void initAccess(String topicId, String studentId, Boolean accessByScore) {
        String sql = String.format("INSERT INTO topic_access (user_id, topic_id, max_sum_score, access_by_score) " +
                "VALUES (:%s, :%s, 0, :%s)", stampUserId, stampTopicId, stampAccessByScore);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(stampUserId, studentId)
                .addValue(stampTopicId, topicId)
                .addValue(stampAccessByScore, accessByScore);
        jdbcOperations.update(sql, params);
    }

    @Override
    public void accessByScore(String userId, String nextTopicId) {
        String sql = String.format("UPDATE topic_access SET max_sum_score = 0, access_by_score = true WHERE user_id = :%s AND topic_id = :%s"
                , stampUserId, stampTopicId);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(stampUserId, userId)
                .addValue(stampTopicId, nextTopicId);
        jdbcOperations.update(sql, params);
    }

    /**
     * The increaseMaxScore method increases the maximum sum score for a user in a specific topic.
     * It updates the max_sum_score field in the topic_access table for the given userId and topicId.
     *
     * @param userId  (type: String): The ID of the user.
     * @param topicId (type: String): The ID of the topic.
     * @param summand (type: Integer): The value to be added to the current maximum sum score.
     */
    @Override
    public void increaseMaxScore(String userId, String topicId, Integer summand) {
        String sql = String.format("UPDATE topic_access SET max_sum_score = max_sum_score + :%s WHERE user_id = :%s AND topic_id = :%s",
                stampMaxSumScore, stampUserId, stampTopicId);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(stampMaxSumScore, summand)
                .addValue(stampUserId, userId)
                .addValue(stampTopicId, topicId);
        try {
            jdbcOperations.update(sql, params);
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TOPIC_ACCESS, userId + topicId));
        }
    }
}
