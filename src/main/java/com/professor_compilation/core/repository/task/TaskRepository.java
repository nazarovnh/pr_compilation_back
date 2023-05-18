package com.professor_compilation.core.repository.task;

import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.model.exception.NoSuchElementFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.professor_compilation.constants.Constants.*;
import static com.professor_compilation.utils.parameter.ParameterUtils.getParameters;
import static com.professor_compilation.utils.rowmapper.EntityRowMapper.getEntityRowMapper;

public class TaskRepository implements ITaskRepository<Task, String> {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final String stampTaskId = "taskId";
    private final String stampTopicId = "topicId";
    private final String stampTaskTitle = "taskTitle";
    private final String stampTaskDescription = "taskDescription";
    private final String stampTaskOrder = "taskOrder";
    private final String stampTimeLimit = "timeLimit";
    private final String stampMemoryLimit = "memoryLimit";
    private final String stampTaskPrice = "taskPrice";

    public TaskRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Task save(final Task task) {
        String taskId = UUID.randomUUID().toString();
        task.setTaskId(taskId);
        String sql = "INSERT INTO task (task_id, topic_id, task_title, task_description, task_order, time_limit, memory_limit, task_price) "
                + "VALUES (:%s, :%s, :%s, :%s, :%s, :%s, :%s, :%s)";
        sql = String.format(sql, stampTaskId, stampTopicId, stampTaskTitle,
                stampTaskDescription, stampTaskOrder, stampTimeLimit, stampMemoryLimit, stampTaskPrice);
        MapSqlParameterSource params = getParameters(task);
        jdbcOperations.update(sql, params);
        return task;
    }

    @Override
    public Optional<Task> findById(final String taskId) {
        try {
            String sql = String.format("SELECT * FROM task WHERE task_id = :%s", stampTaskId);
            Task task = jdbcOperations.queryForObject(sql, new MapSqlParameterSource(stampTaskId, taskId), getEntityRowMapper(Task.class));
            return Optional.of(task);
        } catch (
                EmptyResultDataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TASK, taskId));
        }
    }

    @Override
    public List<Task> findAll() {
        String sql = "SELECT * FROM task";
        return jdbcOperations.query(sql, getEntityRowMapper(Task.class));
    }

    @Override
    public void deleteById(String taskId) {
        String sql = String.format("DELETE FROM task WHERE task_id = :%s", stampTaskId);
        try {
            jdbcOperations.update(sql, new MapSqlParameterSource(stampTaskId, taskId));
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TASK, taskId));
        }
    }

    @Override
    public Task update(final Task task) {
        String sql = String.format("UPDATE task SET topic_id = :%s, task_title = :%s, task_description = :%s, "
                        + "task_order = :%s, time_limit = :%s, memory_limit = :%s, "
                        + "task_price = :%s WHERE task_id = :%s", stampTopicId, stampTaskTitle, stampTaskDescription,
                stampTaskOrder, stampTimeLimit, stampMemoryLimit, stampTaskPrice, stampTaskId);
        MapSqlParameterSource params = getParameters(task);
        try {
            jdbcOperations.update(sql, params);
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TASK, task.getTaskId()));
        }
        return task;
    }

}