package com.professor_compilation.web.controller.compile;

import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.language.Language;
import com.professor_compilation.core.service.compile.ICompileService;
import com.professor_compilation.core.service.subject.SubjectService;
import com.professor_compilation.web.model.compile.CodeRequest;
import com.professor_compilation.web.model.compile.CodeResponse;
import com.professor_compilation.web.model.security.UserCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompileControllerTest {

    @Mock
    private ICompileService compileService;

    @InjectMocks
    private CompileController compileController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void executeTask_WithCorrectRequest_ShouldReturnResponseEntityCodeResponse() throws ProcessException, EnvironmentException {
        String topicId = "testTopicId";
        String taskId = "testTaskId";
        String language = "java";
        MockMultipartFile sourceCodeFile = new MockMultipartFile("sourceCode", "test-file.txt", "text/plain", "Test source code".getBytes());

        UserCredentials userCredentials = mock(UserCredentials.class);
        CodeResponse codeResponse = mock(CodeResponse.class);

        when(compileService.execute(
                anyString(), anyString(),
                any(CodeRequest.class), any(MultipartFile.class),
                any(UserCredentials.class))
        ).thenReturn(codeResponse);

        ResponseEntity<CodeResponse> response = compileController.executeTask(
                topicId, taskId,
                sourceCodeFile, language,
                userCredentials);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
