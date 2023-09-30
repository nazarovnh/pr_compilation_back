package com.professor_compilation.core.repository.topic.access;

import java.io.Serializable;
import java.util.Optional;

public interface ITopicAccessRepository  <T, ID extends Serializable> {
    Optional<T> findById(final ID userId, final ID topicId);

    void updateMaxScore(ID userId, ID topicId, int newMaxScore);

    void initAccess(String topicId, String studentId, Boolean accessByScore);

    void accessByScore(ID userId, ID nextTopicId);

    void increaseMaxScore(ID userId, ID topicId, Integer summand);
}
