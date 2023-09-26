package com.professor_compilation.web.model.topic.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TopicSubjectGetResponse {
    private String topicId;
    private String topicTitle;
    private Integer numbersTasks;
    private Integer completelyTasks;
}
