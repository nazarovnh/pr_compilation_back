package com.professor_compilation.core.repository.solution;

import java.io.Serializable;
import java.util.Optional;

public interface ISolutionAttemptRepository<T, ID extends Serializable> {
    T save(T solutionAttempt);
    Optional<Integer> findMaxScoreForTaskByUserId(final String userId, final String taskId);


    Boolean isAttemptStatusSuccess(ID taskId, ID userId);
}
