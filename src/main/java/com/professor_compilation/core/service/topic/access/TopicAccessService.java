package com.professor_compilation.core.service.topic.access;

import com.professor_compilation.core.entity.topic.access.rdbms.TopicAccessRdbms;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.repository.topic.access.ITopicAccessRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TopicAccessService implements ITopicAccessService {

    private final ITopicRepository<Topic, String> topicRepository;
    private final ITopicAccessRepository<TopicAccessRdbms, String> topicAccessRepository;


    @Override
    public void validateAccessByScore(String userId, String topicId) {
        Topic nextTopic = topicRepository.getNextTopic(topicId);
        TopicAccessRdbms topicAccess = topicAccessRepository.findById(userId, topicId).orElseThrow();
        if (nextTopic.getThresholdScore() <= topicAccess.getMaxSumScore()) {
            topicAccessRepository.accessByScore(userId, nextTopic.getTopicId());
        }
    }

    @Override
    public void updateMaxScore(String userId, String topicId, Integer attemptScore) {
        topicAccessRepository.updateMaxScore(userId, topicId, attemptScore);
        validateAccessByScore(userId, topicId);
    }

    @Override
    public void increaseMaxScore(String userId, String topicId, Integer summand) {
        topicAccessRepository.increaseMaxScore(userId, topicId, summand);
        validateAccessByScore(userId, topicId);
    }
}
