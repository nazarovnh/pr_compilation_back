package com.professor_compilation.web.controller.execution.factory;

import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.language.Language;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.core.service.execution.factory.ExecutionFactory;
import com.professor_compilation.web.model.security.UserCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExecutionFactoryTest {

    @Mock
    private Execution execution;
    @Mock
    private Stream<Execution> streamExecution;
    @Mock
    private Collection<Execution> executionList;


    @Mock
    private MultipartFile sourceCode;

    @Mock
    private TaskInfo taskInfo;

    @Mock
    private UserCredentials userCredentials;
    @Mock
    private Map<Language, Execution> executionsMap;

    private ExecutionFactory executionFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Map<Language, Execution> executionsMap = new HashMap<>();
        executionsMap.put(Language.java, execution);

        when(executionList.stream()).thenReturn(streamExecution);
        when(streamExecution.collect(Collectors.toUnmodifiableMap(Execution::getLanguage, Function.identity()))).thenReturn(executionsMap);
        executionFactory = new ExecutionFactory(executionList);
    }

    @Test
    public void getExecution_WithCorrectParams_ShouldReturnExecution() {
        MultipartFile sourceCode = mock(MultipartFile.class);
        TaskInfo taskInfo = mock(TaskInfo.class);
        UserCredentials userCredentials = mock(UserCredentials.class);
        Language language = Language.java;

        when(taskInfo.getLanguage()).thenReturn(language);
        when(execution.getLanguage()).thenReturn(language);

        Execution result = executionFactory.getExecution(sourceCode, taskInfo, userCredentials);

        verify(execution).build(sourceCode, userCredentials.getUserId(), taskInfo.getTopicId(),
                taskInfo.getTaskId(), taskInfo.getTimeLimit(), taskInfo.getMemoryLimit(), taskInfo.getTestCaseEntity());

        assertSame(execution, result);
    }
}
