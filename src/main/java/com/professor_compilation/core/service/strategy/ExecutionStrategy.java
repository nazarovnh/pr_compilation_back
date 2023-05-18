package com.professor_compilation.core.service.strategy;

import com.professor_compilation.constants.Constants;
import com.professor_compilation.core.entity.solution.rdbms.SolutionAttemptRdbms;
import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.test.TestCase;
import com.professor_compilation.core.model.test.TestCaseResult;
import com.professor_compilation.core.repository.solution.ISolutionAttemptRepository;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.service.container.IContainerService;
import com.professor_compilation.core.service.topic.access.ITopicAccessService;
import com.professor_compilation.utils.process.model.ProcessResult;
import com.professor_compilation.utils.string.StringUtils;
import com.professor_compilation.web.model.compile.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.professor_compilation.constants.Constants.FAILED;
import static com.professor_compilation.constants.Constants.SUCCESS;

@Service
@RequiredArgsConstructor
public abstract class ExecutionStrategy {
    private final IContainerService dockerContainerService;
    private final ISolutionAttemptRepository<SolutionAttemptRdbms, String> solutionAttemptRepository;
    private final ITaskRepository<Task, String> taskRepository;
    private final ITopicRepository<Topic, String> topicRepository;
    private final ITopicAccessService topicAccessService;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Method is responsible for executing the strategy for a given Execution object.
     * It builds the Docker image, runs the container for each test case, verifies the solution, and returns the result.
     *
     * @param execution (type: Execution): The execution object representing the context for the strategy execution.
     * @return CodeResponse: The response object containing the result of the strategy execution, including test case results and execution time.
     * @throws ProcessException: If an error occurs during the strategy execution process.
     */
    public CodeResponse executeStrategy(Execution execution) throws ProcessException {
        List<TestCaseResult> testCaseResultList = new ArrayList<>();
        Long executedTime = 0L;
        try {
            dockerContainerService.buildImage(execution);
            for (TestCase testCase :
                    convertTestCaseFromTestCaseEntity(execution.getTestCases())) {
                ProcessResult processResult = dockerContainerService.runContainer(execution.getImageName(), execution, testCase);
                executedTime += processResult.getExecutedTime();

                StringUtils.removeLineSeparatorProcessResult(processResult);

                if (!verifySolution(testCase.getCorrectOutput(), processResult.getOutputRes())) {
                    buildAndSaveSolutionAttempt(execution, executedTime, FAILED);
                    return new CodeResponse(Constants.resultIncorrectSolution + " " + processResult.getOutputErr(), Constants.statusIncorrectSolution, testCaseResultList, processResult.getExecutedTime());
                }
                testCaseResultList.add(new TestCaseResult(Constants.resultSuccess, Constants.statusSuccess, testCase.getCorrectOutput(), processResult.getOutputRes(), processResult.getExecutedTime()));
            }
            buildAndSaveSolutionAttempt(execution, executedTime, SUCCESS);
        } catch (IOException | ProcessException exception) {
            throw new ProcessException(exception.getMessage());
        } finally {
            dockerContainerService.removedImage(execution.getImageName());
        }

        return new CodeResponse(Constants.resultSuccess, Constants.statusSuccess, testCaseResultList, executedTime);
    }

    private List<TestCase> convertTestCaseFromTestCaseEntity(Collection<TestCaseRdbms> testCaseEntityCollection) {
        return testCaseEntityCollection.stream()
                .map(testCaseEntity -> modelMapper.map(testCaseEntity, TestCase.class))
                .collect(Collectors.toList());
    }

    private Boolean verifySolution(final String correctOutput, final String resultOutput) {
        return correctOutput.equals(resultOutput);
    }

    /**
     * The buildAndSaveSolutionAttempt method is a private helper method that builds and saves a solution attempt based on the execution information, executed time, and status.
     * It creates a SolutionAttemptRdbms object and saves it to the solution attempt repository.
     *
     * @param execution    (type: Execution): The execution object representing the context of the solution attempt.
     * @param executedTime executedTime (type: Long): The execution time of the solution attempt.
     * @param status       (type: String): The status of the solution attempt.
     * @throws IOException: If an I/O error occurs when retrieving the source code bytes.
     */
    private void buildAndSaveSolutionAttempt(final Execution execution, final Long executedTime, final String status) throws IOException {
        Integer attemptScore = taskRepository.findById(execution.getTaskId()).orElseThrow().getTaskPrice();
        SolutionAttemptRdbms solutionAttempt = new SolutionAttemptRdbms(null,
                execution.getUserId(),
                execution.getTaskId(),
                execution.getSourceCode().getOriginalFilename(),
                execution.getSourceCode().getBytes(),
                Objects.equals(status, SUCCESS) ? attemptScore : 0,
                executedTime,
                execution.getLanguage().toString(),
                status);
        Integer maxAttemptScore = solutionAttemptRepository.findMaxScoreForTaskByUserId(execution.getUserId(), execution.getTaskId()).orElse(0);
        if (maxAttemptScore < attemptScore) {
            topicAccessService.increaseMaxScore(execution.getUserId(), execution.getTopicId(), attemptScore - maxAttemptScore);
        }
        solutionAttemptRepository.save(solutionAttempt);
    }
}
