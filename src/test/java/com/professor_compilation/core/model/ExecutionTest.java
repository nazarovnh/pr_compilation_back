package com.professor_compilation.core.model;

import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.language.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExecutionTest {

    @Mock
    private MultipartFile sourceCode;

    @TempDir
    Path tempDir;

    private Execution execution;


    private String userId = "userId";
    private String topicId = "topicId";
    private String taskId = "taskId";
    private Integer timeLimit = 2000;
    private Integer memoryLimit = 512000;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        execution = new Execution() {
            @Override
            public String getExtension() {
                return "cpp";
            }

            @Override
            public Language getLanguage() {
                return Language.cpp;
            }

            @Override
            public Boolean isCompiled() {
                return true;
            }
        };
    }

    @Test
    void build_WithCorrectParams_ShouldBuildExecution() {
        Collection<TestCaseRdbms> testCases = Collections.emptyList();

        execution.build(sourceCode, userId, topicId, taskId, timeLimit, 512000, testCases);

        assertNotNull(execution.getId());
        assertEquals(userId, execution.getUserId());
        assertEquals(topicId, execution.getTopicId());
        assertEquals(taskId, execution.getTaskId());
        assertEquals(timeLimit, execution.getTimeLimit());
        assertEquals(memoryLimit, execution.getMemoryLimit());
        assertEquals("image-" + execution.getId(), execution.getImageName());
        assertEquals("container-" + execution.getId(), execution.getContainerName());
    }

}
