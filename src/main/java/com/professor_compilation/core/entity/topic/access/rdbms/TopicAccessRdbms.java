package com.professor_compilation.core.entity.topic.access.rdbms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicAccessRdbms {
    private String userId;
    private String topicId;
    private Integer maxSumScore;
    private Boolean accessByScore;
    private Boolean accessFromTeacher;
}
