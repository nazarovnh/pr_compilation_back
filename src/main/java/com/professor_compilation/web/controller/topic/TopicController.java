package com.professor_compilation.web.controller.topic;

import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.service.topic.ITopicService;
import com.professor_compilation.web.model.topic.request.TopicCreateRequest;
import com.professor_compilation.web.model.topic.request.TopicPatchRequest;
import com.professor_compilation.web.model.topic.response.TopicAndTasksResponse;
import com.professor_compilation.web.model.topic.response.TopicCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * class TopicController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {
    private final ITopicService topicService;

    @PostMapping("/")
    public ResponseEntity<TopicCreateResponse> createTopic(@RequestBody final TopicCreateRequest topicCreateRequest) {
        return new ResponseEntity(topicService.createTopic(topicCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable final String id) {
        return new ResponseEntity(topicService.getTopicById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<TopicAndTasksResponse> getTopicByIdWithTask(@PathVariable final String id) {
        return new ResponseEntity(topicService.getTopicByIdWithTask(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return new ResponseEntity(topicService.getAllTopics(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Topic> patchTopicById(@PathVariable final String id, @RequestBody final TopicPatchRequest topicPatchRequest) {
        return new ResponseEntity(topicService.patchTopicById(id, topicPatchRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable final String id) {
        topicService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
