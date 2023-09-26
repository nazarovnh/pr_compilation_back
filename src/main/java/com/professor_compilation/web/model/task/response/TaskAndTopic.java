package com.professor_compilation.web.model.task.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskAndTopic {
    private String taskId;
    private String taskTitle;
}
