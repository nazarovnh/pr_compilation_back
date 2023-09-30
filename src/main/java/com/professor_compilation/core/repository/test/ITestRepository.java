package com.professor_compilation.core.repository.test;

import java.util.List;
import java.util.Optional;

public interface ITestRepository<T, ID> {

        T save(final T testCase);

        Optional<T> findById(final ID testId);

        List<T> findAll();

        T update(T entity);

        void deleteById(ID testCaseId);
        void deleteByTaskId(ID taskId);
        List<T> findAllByTaskId(final ID taskId);
}
