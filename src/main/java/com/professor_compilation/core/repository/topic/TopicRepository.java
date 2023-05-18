package com.professor_compilation.core.repository.topic;

import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.model.exception.NoSuchElementFoundException;
import com.professor_compilation.utils.parameter.ParameterUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.professor_compilation.constants.Constants.TOPIC;
import static com.professor_compilation.constants.Constants.getMessageNoSuchEntity;
import static com.professor_compilation.utils.rowmapper.EntityRowMapper.getEntityRowMapper;

public class TopicRepository implements ITopicRepository<Topic, String> {
    private final NamedParameterJdbcOperations jdbcOperations;
    private static final String stampTopicId = "topicId";
    private static final String stampSubjectId = "subjectId";
    private static final String stampTopicTitle = "topicTitle";
    private static final String stampTopicDescription = "topicDescription";
    private static final String stampTopicOrder = "topicOrder";
    private static final String stampThresholdScore = "thresholdScore";

    public TopicRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Topic> findAll() {
        String sql = "SELECT * FROM topic";
        return jdbcOperations.query(sql, getEntityRowMapper(Topic.class));
    }

    @Override
    public Optional<Topic> findById(final String topicId) {
        try {
            String sql = String.format("SELECT * FROM topic WHERE topic_id = :%s", stampTopicId);
            SqlParameterSource parameters = new MapSqlParameterSource(stampTopicId, topicId);
            Topic topic = jdbcOperations.queryForObject(sql, parameters, getEntityRowMapper(Topic.class));
            return Optional.of(topic);
        } catch (
                EmptyResultDataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TOPIC, topicId));
        }
    }

    @Override
    public Collection<Topic> getTopicsBySubjectId(final String subjectId) {
        String sql = String.format("SELECT * FROM topic WHERE subject_id = :%s ORDER BY topic_order", stampSubjectId);
        return jdbcOperations.query(sql, new MapSqlParameterSource(stampSubjectId, subjectId), getEntityRowMapper(Topic.class));
    }

    @Override
    public Topic save(final Topic topic) {
        String topicId = UUID.randomUUID().toString();
        topic.setTopicId(topicId);
        String sql = "INSERT INTO topic (topic_id, subject_id, topic_title, topic_description, topic_order, threshold_score) "
                + "VALUES (:%s, :%s, :%s, :%s, :%s, :%s)";
        String formattedSql = String.format(sql, stampTopicId, stampSubjectId, stampTopicTitle,
                stampTopicDescription, stampTopicOrder, stampThresholdScore);
        MapSqlParameterSource params = ParameterUtils.getParameters(topic);
        int result = jdbcOperations.update(formattedSql, params);
        if (result == 0) {
            return null;
        }
        return topic;
    }

    @Override
    public Topic update(final Topic topic) {
        String sql = String.format("UPDATE topic SET subject_id = :%s, topic_title = :%s," +
                        " topic_description = :%s, topic_order = :%s, threshold_score = :%s WHERE topic_id = :%s",
                stampSubjectId, stampTopicTitle, stampTopicDescription, stampTopicOrder, stampThresholdScore, stampTopicId);
        MapSqlParameterSource params = ParameterUtils.getParameters(topic);
        int result = jdbcOperations.update(sql, params);
        if (result == 0) {
            return null;
        }
        return topic;
    }

    @Override
    public void deleteById(final String topicId) {
        String sql = String.format("DELETE FROM topic WHERE topic_id = :%s", stampTopicId);
        try {
            jdbcOperations.update(sql, new MapSqlParameterSource(stampTopicId, topicId));
        } catch (
                DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TOPIC, topicId));
        }
    }

    @Override
    public Topic getNextTopic(String topicId) {
        String sql = String.format("SELECT * FROM topic" +
                " WHERE topic_order > ( SELECT topic_order FROM topic WHERE topic_id = :%s) ORDER BY topic_order LIMIT 1;", stampTopicId);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(stampTopicId, topicId);
        return jdbcOperations.queryForObject(sql, params, getEntityRowMapper(Topic.class));
    }


}