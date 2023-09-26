package com.professor_compilation.web.model.topic.response;

import com.professor_compilation.web.model.task.response.TaskAndTopic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicAndTasksResponse {
    private String topicId;
    private String subjectId;
    private String topicTitle;
    private String topicDescription;
    private Integer topicOrder;
    private Integer thresholdScore;
    private Collection<TaskAndTopic> tasks;
}
