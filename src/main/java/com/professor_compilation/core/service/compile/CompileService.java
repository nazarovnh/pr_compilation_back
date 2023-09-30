package com.professor_compilation.core.service.compile;

import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.core.service.execution.IExecutionService;
import com.professor_compilation.core.service.task.ITaskService;
import com.professor_compilation.web.model.compile.CodeRequest;
import com.professor_compilation.web.model.compile.CodeResponse;
import com.professor_compilation.web.model.security.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class CompileService implements ICompileService {
    private final ITaskService taskService;
    private final IExecutionService executionService;
    @Override
    public CodeResponse execute(final String topicId, final String taskId, final CodeRequest codeRequest, final MultipartFile sourceCode,
                                final UserCredentials userCredentials) throws ProcessException, EnvironmentException {
        TaskInfo taskInfo = taskService.getTaskById(taskId);
        taskInfo.setLanguage(codeRequest.getLanguage());
        Execution execution = executionService.createExecution(sourceCode, taskInfo, userCredentials);
        CodeResponse codeResponse = executionService.executeCode(execution);
        executionService.removeExecutionEnv(execution);
        return codeResponse;
    }


}
