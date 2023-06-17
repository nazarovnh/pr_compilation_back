package com.professor_compilation.core.repository.solution;

import com.professor_compilation.core.entity.solution.rdbms.SolutionAttemptRdbms;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.Optional;
import java.util.UUID;

import static com.professor_compilation.utils.parameter.ParameterUtils.getParameters;

public class SolutionAttemptRepository implements ISolutionAttemptRepository<SolutionAttemptRdbms, String> {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final String stampAttemptId = "attemptId";
    private final String stampUserId = "userId";
    private final String stampTaskId = "taskId";
    private final String stampFilename = "filename";
    private final String stampFileData = "fileData";
    private final String stampAttemptScore = "attemptScore";
    private final String stampProgrammingLanguage = "programmingLanguage";
    private final String stampExecutionTime = "executionTime";
    private final String stampAttemptStatus = "attemptStatus";

    public SolutionAttemptRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public SolutionAttemptRdbms save(SolutionAttemptRdbms solutionAttempt) {
        String attemptId = UUID.randomUUID().toString();
        solutionAttempt.setAttemptId(attemptId);
        String sql = "INSERT INTO solution_attempt (attempt_id, user_id, task_id, filename," +
                " file_data, attempt_score, execution_time, programming_language, attempt_status)" +
                " VALUES (:%s, :%s, :%s, :%s, :%s, :%s, :%s, :%s, :%s)";
        sql = String.format(sql, stampAttemptId, stampUserId, stampTaskId, stampFilename, stampFileData, stampAttemptScore,
                stampExecutionTime, stampProgrammingLanguage, stampAttemptStatus);
        MapSqlParameterSource params = getParameters(solutionAttempt);
        jdbcOperations.update(sql, params);
        return solutionAttempt;
    }

    @Override
    public Optional<Integer> findMaxScoreForTaskByUserId(final String userId, final String taskId) {
        try {
            String sql = String.format("SELECT MAX(attempt_score) FROM solution_attempt WHERE user_id = :%s AND task_id = :%s", stampUserId, stampTaskId);
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(stampUserId, userId);
            params.addValue(stampTaskId, taskId);
            return Optional.ofNullable(jdbcOperations.queryForObject(sql, params, Integer.class));
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public String getAttemptStatus(String taskId, String userId) {
        return null;
    }
}
