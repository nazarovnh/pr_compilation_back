package com.professor_compilation.web.model.topic.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicCreateResponse {
    private String topicId;
    private String subjectId;
    private String topicTitle;
    private String topicDescription;
    private Integer topicOrder;
    private Integer thresholdScore;
}
