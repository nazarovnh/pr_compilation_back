package com.professor_compilation.core.service.execution.factory;

import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.language.Language;
import com.professor_compilation.core.model.task.TaskInfo;
import com.professor_compilation.web.model.security.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import java.util.function.Function;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The ExecutionFactory class is responsible for creating and providing instances of Execution based on the specified programming language.
 * It maintains a map of available Execution objects for each language and retrieves the appropriate instance based on the provided language.
 */
@Component
public class ExecutionFactory {
    private Map<Language, Execution> executionsMap;

    @Autowired
    private ExecutionFactory(List<Execution> exceptions) {
        executionsMap = exceptions.stream().collect(Collectors.toUnmodifiableMap(Execution::getLanguage, Function.identity()));
    }

    /**
     * Method retrieves the appropriate Execution instance based on the specified programming language in the TaskInfo object.
     * It builds the execution instance with the provided source code, task information, and user credentials.
     * @param sourceCode  (type: MultipartFile): The source code file.
     * @param taskInfo (type: TaskInfo): The task information.
     * @param userCredentials  (type: UserCredentials): The user credentials.
     * @return The appropriate Execution instance for the specified programming language.
     */
    public Execution getExecution(final MultipartFile sourceCode, final TaskInfo taskInfo, final UserCredentials userCredentials) {
        Execution execution = Optional.ofNullable(executionsMap.get(taskInfo.getLanguage())).orElseThrow(IllegalArgumentException::new);
        execution.build(sourceCode, userCredentials.getUserId(), taskInfo.getTopicId(), taskInfo.getTaskId(), taskInfo.getTimeLimit(), taskInfo.getMemoryLimit(), taskInfo.getTestCaseEntity());
        return execution;
    }
}
