package com.professor_compilation.core.service.compile;

import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.web.model.compile.CodeRequest;
import com.professor_compilation.web.model.compile.CodeResponse;
import com.professor_compilation.web.model.security.UserCredentials;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICompileService {
    /**
     * The execute method executes the provided code by creating an execution environment, executing the code, and returning the response.
     * It involves retrieving task information, creating an Execution instance, executing the code, and removing the execution environment.
     * @param topicId  (type: String): The ID of the topic.
     * @param taskId  (type: String): The ID of the task.
     * @param codeRequest  (type: CodeRequest): The code request object containing the language and other details.
     * @param sourceCode  (type: MultipartFile): The source code file.
     * @param userCredentials (type: UserCredentials): The user credentials.
     * @return CodeResponse: The response containing the result and other details of the code execution.
     * @throws ProcessException: If an error occurs during the code execution process.
     * @throws EnvironmentException: If an error occurs while setting up or removing the execution environment.
     */
    CodeResponse execute(final String topicId, final String taskId, final CodeRequest codeRequest, final MultipartFile sourceCode,
                         final UserCredentials userCredentials) throws ProcessException, EnvironmentException;
}
