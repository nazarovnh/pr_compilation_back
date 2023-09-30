package com.professor_compilation.core.entity.task.jsonb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskDataJsonb {

    @JsonProperty("task_title")
    private String taskTitle;

    @JsonProperty("task_description")
    private String taskDescription;

    @JsonProperty("time_limit")
    private int timeLimit;

    @JsonProperty("memory_limit")
    private int memoryLimit;
}
