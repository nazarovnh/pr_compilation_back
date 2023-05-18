package com.professor_compilation.core.repository.topic;

import com.professor_compilation.core.entity.topic.rdbms.Topic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface ITopicRepository  <T, ID extends Serializable> {
    Optional<T> findById(final ID topicId);

    Collection<T> getTopicsBySubjectId(final ID subjectId);
    Collection<T> findAll();

    T save(final T topic);

    T update(final T topic);

    void deleteById(final String id);

    Topic getNextTopic(ID topicId);
}
