package com.professor_compilation.service.compile;

import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.language.Language;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.core.service.compile.CompileService;
import com.professor_compilation.core.service.execution.IExecutionService;
import com.professor_compilation.core.service.task.ITaskService;
import com.professor_compilation.web.model.compile.CodeRequest;
import com.professor_compilation.web.model.compile.CodeResponse;
import com.professor_compilation.web.model.security.UserCredentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompileServiceTest {

    @Mock
    private ITaskService taskService;

    @Mock
    private IExecutionService executionService;

    @InjectMocks
    private CompileService compileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void execute_WithCorrectParams_ShouldBuildCodeResponse() throws ProcessException, EnvironmentException {
        String topicId = "mockTopicId";
        String taskId = "mockTaskId";
        CodeRequest codeRequest = mock(CodeRequest.class);
        MultipartFile sourceCode = mock(MultipartFile.class);
        UserCredentials userCredentials = mock(UserCredentials.class);

        TaskInfo taskInfo = mock(TaskInfo.class);
        Execution execution = mock(Execution.class);
        CodeResponse codeResponse = mock(CodeResponse.class);
        Language language = Language.cpp;

        when(codeRequest.getLanguage()).thenReturn(language);
        doNothing().when(taskInfo).setLanguage(language);

        when(taskService.getTaskById(taskId)).thenReturn(taskInfo);
        when(executionService.createExecution(sourceCode, taskInfo, userCredentials)).thenReturn(execution);
        when(executionService.executeCode(execution)).thenReturn(codeResponse);
        doNothing().when(executionService).removeExecutionEnv(execution);

        CodeResponse result = compileService.execute(topicId, taskId, codeRequest, sourceCode, userCredentials);

        assertNotNull(result);
        assertEquals(result, codeResponse);

        verify(taskService, times(1)).getTaskById(taskId);
        verify(executionService, times(1)).createExecution(sourceCode, taskInfo, userCredentials);
        verify(executionService, times(1)).createExecution(sourceCode, taskInfo, userCredentials);
        verify(executionService, times(1)).removeExecutionEnv(execution);
    }

    @Test
    public void execute_WithCorrectParams_ShouldThrowProcessException() throws ProcessException, EnvironmentException {
        String topicId = "mockTopicId";
        String taskId = "mockTaskId";
        CodeRequest codeRequest = mock(CodeRequest.class);
        MultipartFile sourceCode = mock(MultipartFile.class);
        UserCredentials userCredentials = mock(UserCredentials.class);

        TaskInfo taskInfo = mock(TaskInfo.class);
        Execution execution = mock(Execution.class);
        Language language = Language.cpp;

        when(codeRequest.getLanguage()).thenReturn(language);
        doNothing().when(taskInfo).setLanguage(language);

        when(taskService.getTaskById(taskId)).thenReturn(taskInfo);
        when(executionService.createExecution(sourceCode, taskInfo, userCredentials)).thenReturn(execution);

        when(executionService.executeCode(execution)).thenThrow(ProcessException.class);

        assertThrows(ProcessException.class, () -> {
            CodeResponse result = compileService.execute(topicId, taskId, codeRequest, sourceCode, userCredentials);
        });
    }

    @Test
    public void execute_WithCorrectParams_ShouldThrowEnvironmentException() throws ProcessException, EnvironmentException {
        String topicId = "mockTopicId";
        String taskId = "mockTaskId";
        CodeRequest codeRequest = mock(CodeRequest.class);
        MultipartFile sourceCode = mock(MultipartFile.class);
        UserCredentials userCredentials = mock(UserCredentials.class);

        TaskInfo taskInfo = mock(TaskInfo.class);
        Execution execution = mock(Execution.class);
        Language language = Language.cpp;

        when(codeRequest.getLanguage()).thenReturn(language);
        doNothing().when(taskInfo).setLanguage(language);

        when(taskService.getTaskById(taskId)).thenReturn(taskInfo);
        when(executionService.createExecution(sourceCode, taskInfo, userCredentials)).thenReturn(execution);

        when(executionService.executeCode(execution)).thenThrow(EnvironmentException.class);

        assertThrows(EnvironmentException.class, () -> {
            CodeResponse result = compileService.execute(topicId, taskId, codeRequest, sourceCode, userCredentials);
        });
    }
}
