package com.professor_compilation.core.repository.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.professor_compilation.core.entity.task.jsonb.TaskJsonb;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TaskJsonbRepository implements ITaskRepository<TaskJsonb, String> {

    private final JdbcOperations jdbcOperations;

    public TaskJsonbRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Optional<TaskJsonb> findById(String taskId) {
        String sql = "SELECT task FROM task_jsonb WHERE (task ->> 'task_id')::text = ?";
        try {
            TaskJsonb taskJsonb = jdbcOperations.queryForObject(sql, new Object[]{taskId}, (rs, rowNum) -> {
                String task = rs.getString("task");
                return jsonToTask(task);
            });
            return Optional.ofNullable(taskJsonb);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }


    @Override
    public List<TaskJsonb> findAll() {
        String sql = "SELECT task FROM task_jsonb";
        return jdbcOperations.query(sql, (rs, rowNum) -> jsonToTask(rs.getString("task")));
    }


    @Override
    public TaskJsonb save(TaskJsonb entity) {
        String sql = "INSERT INTO task_jsonb (task) VALUES (?) RETURNING task_id";
        String taskJson = taskToJson(entity);
        String taskId = jdbcOperations.queryForObject(sql, String.class, taskJson);
        entity.setTaskId(taskId);
        return entity;
    }


    @Override
    public TaskJsonb update(TaskJsonb entity) {
        String sql = "UPDATE task_jsonb SET task = ? WHERE (task ->> 'task_id')::text = ?";
        String taskJson = taskToJson(entity);
        jdbcOperations.update(sql, taskJson, entity.getTaskId());
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Optional<TaskJsonb> task = findById(id);
        String sql = "DELETE FROM task_jsonb WHERE (task ->> 'task_id')::text = ?";
        jdbcOperations.update(sql, id);
    }


    private String taskToJson(TaskJsonb taskJsonb) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(taskJsonb);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private TaskJsonb jsonToTask(String taskJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(taskJson, TaskJsonb.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
