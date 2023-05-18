package com.professor_compilation.core.model.execution;

import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import com.professor_compilation.core.model.language.Language;
import com.professor_compilation.utils.file.FileUtils;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;

/**
 * The Execution class is an abstract class representing an execution context for a specific task or code execution.
 * It contains various properties and methods related to the execution environment and configuration.
 */
@Data
public abstract class Execution {

    public static final Integer DEFAULT_TIMEOUT = 1000;
    private MultipartFile sourceCode;
    private String id;
    private String userId;
    private String path;

    private String topicId;
    private String taskId;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Collection<TestCaseRdbms> testCases;
    private String imageName;
    private String containerName;

    /**
     * Builds the execution context with the provided parameters.
     *
     * @param sourceCode  (type: MultipartFile): The source code file associated with the execution.
     * @param userId      (type: String): The identifier of the user associated with the execution.
     * @param topicId     (type: String): The path of the execution directory.
     * @param taskId      (type: String): The identifier of the task related to the execution.
     * @param timeLimit   (type: Integer): The time limit for the execution in milliseconds.
     * @param memoryLimit (type: Integer): The memory limit for the execution in bytes.
     */
    public void build(final MultipartFile sourceCode,
                      final String userId, String topicId,
                      final String taskId,
                      final Integer timeLimit,
                      final Integer memoryLimit,
                      final Collection<TestCaseRdbms> testCases) {
        this.id = String.valueOf(UUID.randomUUID());
        this.sourceCode = sourceCode;
        this.userId = userId;
        this.topicId = topicId;
        this.taskId = taskId;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.testCases = testCases;
        this.path = "executions" + "/" + getLanguage() + "/" + "execution-" + id;
        this.imageName = "image-" + id;
        this.containerName = "container-" + id;
    }

    /**
     * Creates the execution environment by creating the execution directory and saving the source code file locally.
     *
     * @throws IOException - IOException
     */
    public void createEnvironmentForExecution() throws IOException {
        Files.createDirectories(Paths.get(this.path));
        FileUtils.saveFilesLocally(sourceCode, path, sourceCode.getName() + '.' + getExtension());
        copyDockerFilesToExecutionDirectory();
    }

    /**
     * Copies the Docker files to the execution directory.
     *
     * @throws IOException - IOException
     */
    public void copyDockerFilesToExecutionDirectory() throws IOException {
        FileUtils.copyFileInDirectory("images/" + getLanguage().toString() + "/Dockerfile", path + "/Dockerfile");
    }

    public String getFullFileName() {
        return sourceCode.getName() + '.' + getExtension();
    }

    public abstract String getExtension();

    public abstract Language getLanguage();

    public abstract Boolean isCompiled();
}

