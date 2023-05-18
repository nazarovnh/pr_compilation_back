package com.professor_compilation.core.service.execution;

import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.web.model.compile.CodeResponse;
import com.professor_compilation.web.model.security.UserCredentials;
import org.springframework.web.multipart.MultipartFile;

public interface IExecutionService {

    Execution createExecution(final MultipartFile sourceCode, final TaskInfo taskInfo, final UserCredentials userCredentials);

    CodeResponse executeCode(final Execution execution) throws EnvironmentException, ProcessException;
    void removeExecutionEnv(final Execution execution) throws EnvironmentException;
}
