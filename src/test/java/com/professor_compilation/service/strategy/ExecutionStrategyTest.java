package com.professor_compilation.service.strategy;

import com.professor_compilation.core.entity.solution.rdbms.SolutionAttemptRdbms;
import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.repository.solution.ISolutionAttemptRepository;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.service.container.IContainerService;
import com.professor_compilation.core.service.topic.access.ITopicAccessService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class ExecutionStrategyTest {

    @Mock
    private IContainerService dockerContainerService;

    @Mock
    private ISolutionAttemptRepository<SolutionAttemptRdbms, String> solutionAttemptRepository;

    @Mock
    private ITaskRepository<Task, String> taskRepository;

    @Mock
    private ITopicRepository<Topic, String> topicRepository;

    @Mock
    private ITopicAccessService topicAccessService;

    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    public void executeStrategy_WithCorrectParams_ShouldReturnCodeResponse(){

    }
}
