package com.professor_compilation.web.model.task.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TaskCreateRequest {
    @JsonProperty("topicId")
    private final String topicId;
    @JsonProperty("taskTitle")
    private final String taskTitle;
    @JsonProperty("taskDescription")
    private final String taskDescription;
    @JsonProperty("taskOrder")
    private final Integer taskOrder;
    @JsonProperty("timeLimit")
    private final Integer timeLimit;
    @JsonProperty("memoryLimit")
    private final Integer memoryLimit;
    @JsonProperty("taskPrice")
    private final Integer taskPrice;
    @JsonProperty("testCases")
    private final List<TestCaseRdbms> testCases;
}
