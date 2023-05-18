package com.professor_compilation.core.service.topic.access;

public interface ITopicAccessService {
    void validateAccessByScore(final String userId, final String topicId);

    void updateMaxScore(String userId, String topicId, Integer attemptScore);


    void increaseMaxScore(String userId, String topicId, Integer summand);
}
