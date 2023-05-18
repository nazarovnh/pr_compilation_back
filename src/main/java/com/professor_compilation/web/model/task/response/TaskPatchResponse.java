package com.professor_compilation.web.model.task.response;

import com.professor_compilation.core.model.test.TestCase;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TaskPatchResponse {
    private final String taskId;
    private final String topicId;
    private final String taskTitle;
    private final String taskDescription;
    private final Integer taskOrder;
    private final Integer timeLimit;
    private final Integer memoryLimit;
    private final Integer taskPrice;
    private final List<TestCase> testCases;
}
