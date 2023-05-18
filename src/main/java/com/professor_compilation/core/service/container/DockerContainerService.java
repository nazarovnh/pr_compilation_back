package com.professor_compilation.core.service.container;

import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.test.TestCase;
import com.professor_compilation.utils.process.ProcessUtils;
import com.professor_compilation.utils.process.model.ProcessResult;

public class DockerContainerService implements IContainerService {

    private final String Dockerfile = "Dockerfile";

    public DockerContainerService() {
    }

    @Override
    public ProcessResult buildImage(final Execution execution) throws ProcessException {
        String dockerfilePath = execution.getPath() + "/" + Dockerfile;
        String[] buildCommand =
                new String[]{
                        "docker",
                        "image",
                        "build",
                        "-f", dockerfilePath,
                        "-t", execution.getImageName(),
                        "--build-arg", "EXECUTION_PATH" + "=" + execution.getPath(),
                        "--build-arg", "SOURCE_CODE_FILE_NAME" + "=" + execution.getFullFileName(),
                        "."};
        return ProcessUtils.executeCommand(buildCommand, execution.getTimeLimit());
    }

    @Override
    public ProcessResult runContainer(final String imageName, final Execution execution, final TestCase testCase) throws ProcessException {
        String[] runCommand =
                new String[]{
                        "docker",
                        "run",
                        "--rm",
                        "--name", execution.getContainerName(),
                        "-e", "EXECUTION_PATH" + "=" + execution.getPath(),
                        "-e", "SOURCE_CODE_FILE_NAME" + "=" + execution.getFullFileName(),
                        "-e", "INPUT" + "=" + testCase.getInput(),
                        imageName};
        return ProcessUtils.executeCommand(runCommand, execution.getTimeLimit());
    }

    @Override
    public ProcessResult removedImage(String imageName) throws ProcessException {
        String[] removedCommand =
                new String[]{
                        "docker",
                        "rmi", imageName, "-f"
                };
        return ProcessUtils.executeCommand(removedCommand, Execution.DEFAULT_TIMEOUT);
    }


}
