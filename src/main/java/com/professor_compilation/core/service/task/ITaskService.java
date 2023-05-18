package com.professor_compilation.core.service.task;

import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.web.model.task.request.TaskCreateRequest;
import com.professor_compilation.web.model.task.request.TaskPatchRequest;

import java.util.List;

public interface ITaskService {
    Task createTask(final TaskCreateRequest taskCreateRequest);

    List<TaskInfo> findAll();

    TaskInfo getTaskById(final String taskId);
    void deleteById(final String taskId);

}
