package com.professor_compilation.core.service.execution;

import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.core.service.execution.factory.ExecutionFactory;
import com.professor_compilation.core.service.strategy.ExecutionStrategy;
import com.professor_compilation.utils.file.FileUtils;
import com.professor_compilation.web.model.compile.CodeResponse;
import com.professor_compilation.web.model.security.UserCredentials;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ExecutionService implements IExecutionService {

    private final ExecutionFactory executionFactory;
    private final ExecutionStrategy compilerStrategy;
    private final ExecutionStrategy interpreterStrategy;

    public ExecutionService(final ExecutionFactory executionFactory, final ExecutionStrategy compilerStrategy, final ExecutionStrategy interpreterStrategy) {
        this.executionFactory = executionFactory;
        this.compilerStrategy = compilerStrategy;
        this.interpreterStrategy = interpreterStrategy;
    }

    @Override
    public Execution createExecution(final MultipartFile sourceCode, final TaskInfo taskInfo, final UserCredentials userCredentials) {
        return executionFactory.getExecution(sourceCode, taskInfo, userCredentials);
    }

    @Override
    public CodeResponse executeCode(Execution execution) throws EnvironmentException, ProcessException {
        ExecutionStrategy executionStrategy = getBuildStrategy(execution.isCompiled());
        buildEnvironmentForExecution(execution);
        return executionStrategy.executeStrategy(execution);
    }

    @Override
    public void removeExecutionEnv(Execution execution) throws EnvironmentException {
        try {
            FileUtils.deleteDirectory(execution.getPath());
        } catch (IOException ioException) {
            throw new EnvironmentException(ioException.getMessage());
        }
    }

    public ExecutionStrategy getBuildStrategy(Boolean isCompiled) {
        if (isCompiled) return compilerStrategy;
        return interpreterStrategy;
    }

    public void buildEnvironmentForExecution(Execution execution) throws EnvironmentException {
        try {
            execution.createEnvironmentForExecution();
        } catch (IOException ioException) {
            throw new EnvironmentException(ioException.getMessage());
        }
    }
}
