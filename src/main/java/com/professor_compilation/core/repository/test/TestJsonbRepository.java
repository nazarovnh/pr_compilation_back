package com.professor_compilation.core.repository.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.professor_compilation.core.entity.test.jsonb.TestCaseDataJsonb;
import com.professor_compilation.core.entity.test.jsonb.TestCaseJsonb;
import org.springframework.jdbc.core.JdbcOperations;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestJsonbRepository implements ITestRepository<TestCaseJsonb, String> {

    private final JdbcOperations jdbcOperations;

    public TestJsonbRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    public TestCaseJsonb save(TestCaseJsonb testCaseJsonb) {
        String query = "INSERT INTO test_case_jsonb(test_case) VALUES (?)";
        jdbcOperations.update(query, testCaseJsonb.toMap());
        return testCaseJsonb;
    }

    public Optional<TestCaseJsonb> findById(String testCaseId) {
        String query = "SELECT * FROM test_case_jsonb WHERE test_case->>'test_case_id' = ?";
        List<TestCaseJsonb> testCases = jdbcOperations.query(query, new Object[]{testCaseId}, (rs, rowNum) ->
                TestCaseJsonb.fromMap(rs.getObject("test_case", java.util.Map.class)));
        return testCases.isEmpty() ? Optional.empty() : Optional.of(testCases.get(0));
    }

    @Override
    public List<TestCaseJsonb> findAll() {
        String sql = "SELECT * FROM test_case_jsonb";
        return jdbcOperations.query(sql, (rs, rowNum) -> TestCaseJsonb.fromMap(rs.getObject("test_case", Map.class)));
    }

    @Override
    public TestCaseJsonb update(TestCaseJsonb testCaseJsonb) {
        String query = "UPDATE test_case_jsonb SET test_case = ? WHERE test_case->>'test_case_id' = ?";
        jdbcOperations.update(query, testCaseJsonb.toMap(), testCaseJsonb.getTestCaseId());
        return findById(testCaseJsonb.getTestCaseId()).get();
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void deleteByTaskId(String taskId) {

    }

    @Override
    public List<TestCaseJsonb> findAllByTaskId(String taskId) {
        String sql = "SELECT * FROM test_case_jsonb WHERE test_case->>'task_id' = ?";
        return jdbcOperations.query(sql, new Object[]{taskId}, (rs, rowNum) -> TestCaseJsonb.fromMap(rs.getObject("test_case", Map.class)));
    }

}