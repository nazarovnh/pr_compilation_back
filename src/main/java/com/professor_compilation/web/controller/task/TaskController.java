package com.professor_compilation.web.controller.task;

import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.core.service.task.ITaskService;
import com.professor_compilation.web.model.task.request.TaskCreateRequest;
import com.professor_compilation.web.model.task.request.TaskPatchRequest;
import com.professor_compilation.web.model.task.response.TaskGetResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * class TaskController
 */
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final ITaskService taskService;

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateRequest task) {
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskInfo>> getAllTasks() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskGetResponse> getTask(final @PathVariable String taskId) {
        return new ResponseEntity<>(taskService.getTaskInfoById(taskId), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(final @PathVariable String id) {
        taskService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
