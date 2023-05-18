package com.professor_compilation.core.repository.test;

import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
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

public class TestRepository implements ITestRepository<TestCaseRdbms, String> {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final String stampTestCaseId = "testCaseId";
    private final String stampTaskId = "taskId";
    private final String stampInput = "input";
    private final String stampCorrectOutput = "correctOutput";

    public TestRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public TestCaseRdbms save(TestCaseRdbms testCase) {
        String testCaseId = UUID.randomUUID().toString();
        testCase.setTestCaseId(testCaseId);
        String sql = "INSERT INTO test_case (test_case_id, task_id, input, correct_output) "
                + "VALUES (:%s, :%s, :%s, :%s)";
        sql = String.format(sql, stampTestCaseId, stampTaskId, stampInput, stampCorrectOutput);
        MapSqlParameterSource params = getParameters(testCase);
        jdbcOperations.update(sql, params);
        return testCase;
    }

    @Override
    public Optional<TestCaseRdbms> findById(String testCaseId) {
        try {
            String sql = String.format("SELECT * FROM test_case WHERE test_case_id = :%s", stampTestCaseId);
            TestCaseRdbms testCase = jdbcOperations.queryForObject(sql, new MapSqlParameterSource(stampTestCaseId, testCaseId), getEntityRowMapper(TestCaseRdbms.class));
            return Optional.of(testCase);
        } catch (
                EmptyResultDataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TESTCASE, testCaseId));
        }
    }

    @Override
    public List<TestCaseRdbms> findAll() {
        String sql = "SELECT * FROM test_case";
        return jdbcOperations.query(sql, getEntityRowMapper(TestCaseRdbms.class));
    }

    @Override
    public TestCaseRdbms update(TestCaseRdbms testCase) {
        String sql = String.format("UPDATE test_case SET task_id = :%s, input = :%s, correct_output = :%s " +
                " WHERE test_case_id = :%s", stampTaskId, stampInput, stampCorrectOutput, stampTestCaseId);
        MapSqlParameterSource params = getParameters(testCase);
        try {
            jdbcOperations.update(sql, params);
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TESTCASE, testCase.getTaskId()));
        }
        return testCase;
    }


    @Override
    public void deleteById(String testCaseId) {
        String sql = String.format("DELETE FROM test_case WHERE test_case_id = :%s", stampTestCaseId);
        try {
            jdbcOperations.update(sql, new MapSqlParameterSource(stampTestCaseId, testCaseId));
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TESTCASE, testCaseId));
        }
    }

    @Override
    public void deleteByTaskId(String taskId) {
        String sql = String.format("DELETE FROM test_case WHERE task_id = :%s", stampTaskId);
        try {
            jdbcOperations.update(sql, new MapSqlParameterSource(stampTaskId, taskId));
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(TESTCASE, taskId));
        }
    }


    @Override
    public List<TestCaseRdbms> findAllByTaskId(String taskId) {
        String sql = String.format("SELECT * FROM test_case WHERE task_id = :%s", stampTaskId);
        return jdbcOperations.query(sql, new MapSqlParameterSource(stampTaskId, taskId), getEntityRowMapper(TestCaseRdbms.class));
    }

}
