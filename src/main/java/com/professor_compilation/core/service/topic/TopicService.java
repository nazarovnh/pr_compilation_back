package com.professor_compilation.core.service.topic;

import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.utils.convert.ConvertUtils;
import com.professor_compilation.web.model.task.response.TaskAndTopic;
import com.professor_compilation.web.model.topic.request.TopicCreateRequest;
import com.professor_compilation.web.model.topic.request.TopicPatchRequest;
import com.professor_compilation.web.model.topic.response.TopicAndTasksResponse;
import com.professor_compilation.web.model.topic.response.TopicCreateResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TopicService implements ITopicService{
    private final ITopicRepository<Topic, String> topicRepository;
    private final ITaskRepository<Task, String> taskRepository;
    private final ModelMapper modelMapper;
    @Override
    public TopicCreateResponse createTopic(TopicCreateRequest topicCreateRequest) {
        Topic topic = topicRepository.save(ConvertUtils.convert(topicCreateRequest, Topic.class));
        return ConvertUtils.convert(topic, TopicCreateResponse.class);
    }

    @Override
    public Topic getTopicById(String topicId) {
        return topicRepository.findById(topicId).orElseThrow();
    }

    @Override
    public List<Topic> getAllTopics() {
        return new ArrayList<>(topicRepository.findAll());
    }

    @Override
    public Topic patchTopicById(String topicId, TopicPatchRequest topicPatchRequest) {
        Topic topic = ConvertUtils.convert(topicPatchRequest, Topic.class);
        topic.setTopicId(topicId);
        return topicRepository.update(topic);
    }

    @Override
    public void deleteById(String topicId) {
        topicRepository.deleteById(topicId);
    }

    @Override
    public TopicAndTasksResponse getTopicByIdWithTask(String topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow();
        List<Task> tasks = new ArrayList<>(taskRepository.findByTopicId(topicId));
        TopicAndTasksResponse topicAndTasksResponse = modelMapper.map(topic, TopicAndTasksResponse.class);

        List<TaskAndTopic> taskAndTopics = tasks.stream()
                .map(task -> modelMapper.map(task, TaskAndTopic.class))
                .collect(Collectors.toList());

        topicAndTasksResponse.setTasks(taskAndTopics);
        return topicAndTasksResponse;
    }
}
