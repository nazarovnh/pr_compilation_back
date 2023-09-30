package com.professor_compilation.core.service.task;

import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.test.ITestRepository;
import com.professor_compilation.utils.convert.ConvertUtils;
import com.professor_compilation.web.model.task.request.TaskCreateRequest;
import com.professor_compilation.web.model.task.response.TaskGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService implements ITaskService {

    private final ITaskRepository<Task, String> taskRepository;

    private final ITestRepository<TestCaseRdbms, String> testRepository;

    @Override
    public Task createTask(TaskCreateRequest taskCreateRequest) {
        Task newTask = taskRepository.save(ConvertUtils.convert(taskCreateRequest, Task.class));
        for (TestCaseRdbms testCase :
                taskCreateRequest.getTestCases()) {
            testRepository.save(testCase);
        }
        return newTask;
    }

    @Override
    public List<TaskInfo> findAll() {
        List<Task> tasks = new ArrayList<>(taskRepository.findAll());
        List<TaskInfo> taskInfos = new ArrayList<>();
        for (Task task :
                tasks) {
            TaskInfo taskInfo = ConvertUtils.convert(task, TaskInfo.class);
            taskInfo.setTestCaseEntity(testRepository.findAllByTaskId(task.getTaskId()));
            taskInfos.add(taskInfo);
        }
        return taskInfos;
    }

    @Override
    public TaskInfo getTaskById(String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        List<TestCaseRdbms> testCases = testRepository.findAllByTaskId(taskId);
        TaskInfo taskInfo = ConvertUtils.convert(task, TaskInfo.class);
        taskInfo.setTestCaseEntity(testCases);
        return taskInfo;
    }

    @Override
    public TaskGetResponse getTaskInfoById(String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        List<TestCaseRdbms> testCases = testRepository.findAllByTaskId(taskId);
        TaskGetResponse taskInfo = ConvertUtils.convert(task, TaskGetResponse.class);
        taskInfo.setExampleInput(testCases.get(0).getInput());
        taskInfo.setExampleCorrectOutput(testCases.get(0).getCorrectOutput());
        return taskInfo;
    }

    @Override
    public void deleteById(String taskId) {
        testRepository.deleteById(taskId);
        taskRepository.deleteById(taskId);
    }

}
