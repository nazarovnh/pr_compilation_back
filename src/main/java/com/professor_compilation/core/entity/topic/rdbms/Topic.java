package com.professor_compilation.core.entity.topic.rdbms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Topic {
    private String topicId;
    private String subjectId;
    private String topicTitle;
    private String topicDescription;
    private Integer topicOrder;
    private Integer thresholdScore;

    public Topic() {}

}
