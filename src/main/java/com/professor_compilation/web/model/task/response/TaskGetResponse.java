package com.professor_compilation.web.model.task.response;

import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import com.professor_compilation.core.model.test.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskGetResponse {
    private String taskTitle;
    private String taskDescription;
    private Integer taskOrder;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String exampleInput;
    private String exampleCorrectOutput;
}
