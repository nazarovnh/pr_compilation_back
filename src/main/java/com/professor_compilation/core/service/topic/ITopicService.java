package com.professor_compilation.core.service.topic;

import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.web.model.topic.request.TopicCreateRequest;
import com.professor_compilation.web.model.topic.request.TopicPatchRequest;
import com.professor_compilation.web.model.topic.response.TopicAndTasksResponse;
import com.professor_compilation.web.model.topic.response.TopicCreateResponse;

import java.util.List;

public interface ITopicService {
    TopicCreateResponse createTopic(final TopicCreateRequest topicCreateRequest);

    Topic getTopicById(final String topicId);

    List<Topic> getAllTopics();

    Topic patchTopicById(final String topicId, final TopicPatchRequest topicPatchRequest);

    void deleteById(final String topicId);

    TopicAndTasksResponse getTopicByIdWithTask(final String topicId);
}
