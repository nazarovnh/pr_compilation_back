package com.professor_compilation.core.entity.task.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.professor_compilation.core.entity.task.ITask;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskJsonb implements ITask {

    @JsonProperty("task_id")
    private String taskId;
    @JsonProperty("task_title")
    private String taskTitle;

    @JsonProperty("task_description")
    private String taskDescription;

    @JsonProperty("time_limit")
    private int timeLimit;

    @JsonProperty("memory_limit")
    private int memoryLimit;
}