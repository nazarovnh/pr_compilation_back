package com.professor_compilation.core.entity.task.rdbms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {

    private String taskId;
    private String topicId;
    private String taskTitle;
    private String taskDescription;
    private Integer taskOrder;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Integer taskPrice;

    public Task() {

    }
}
