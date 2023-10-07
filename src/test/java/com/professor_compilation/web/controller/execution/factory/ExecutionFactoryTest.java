package com.professor_compilation.web.controller.execution.factory;

import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.language.Language;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.core.service.execution.factory.ExecutionFactory;
import com.professor_compilation.web.model.security.UserCredentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExecutionFactoryTest {

    @Mock
    private MultipartFile sourceCode;

    @Mock
    private TaskInfo taskInfo;

    @Mock
    private UserCredentials userCredentials;

    @Autowired
    private ExecutionFactory executionFactory;


    @Test
    public void getExecution_WithCorrectParams_ShouldReturnExecution() {
        TestCaseRdbms testCaseEntity = mock(TestCaseRdbms.class);

        Collection<TestCaseRdbms> testCases = Arrays.asList(testCaseEntity);

        when(taskInfo.getLanguage()).thenReturn(Language.cpp);
        when(userCredentials.getUserId()).thenReturn("userId");
        when(taskInfo.getTopicId()).thenReturn("topicId");
        when(taskInfo.getTaskId()).thenReturn("taskId");
        when(taskInfo.getTimeLimit()).thenReturn(1000);
        when(taskInfo.getMemoryLimit()).thenReturn(256);
        when(taskInfo.getTestCaseEntity()).thenReturn(testCases);

        Execution execution = executionFactory.getExecution(sourceCode, taskInfo, userCredentials);

        assertNotNull(execution);

        assertEquals(execution.getTopicId(), "topicId");
        assertEquals(execution.getTaskId(), "taskId");
        assertEquals(execution.getTimeLimit(), 1000);
        assertEquals(execution.getMemoryLimit(), 256);
        assertEquals(execution.getTestCases().size(), 1);
    }

    @Test
    public void getExecution_WithNotExistsLanguage_ShouldThrowIllegalArgumentException() {
        when(taskInfo.getLanguage()).thenThrow(IllegalArgumentException.class);

        Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    Execution execution = executionFactory.getExecution(sourceCode, taskInfo, userCredentials);
                });

    }
}
