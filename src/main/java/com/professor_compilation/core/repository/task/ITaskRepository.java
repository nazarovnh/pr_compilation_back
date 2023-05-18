package com.professor_compilation.core.repository.task;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface ITaskRepository<T, ID extends Serializable> {

    Optional<T> findById(final ID taskId);

    Collection<T> findAll();

    T save(T task);

    void deleteById(ID taskId);

    T update(T entity);
}
