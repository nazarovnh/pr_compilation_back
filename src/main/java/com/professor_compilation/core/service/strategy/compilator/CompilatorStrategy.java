package com.professor_compilation.core.service.strategy.compilator;

import com.professor_compilation.core.entity.solution.rdbms.SolutionAttemptRdbms;
import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.repository.solution.ISolutionAttemptRepository;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.service.container.IContainerService;
import com.professor_compilation.core.service.strategy.ExecutionStrategy;
import com.professor_compilation.core.service.topic.access.ITopicAccessService;

public class CompilatorStrategy extends ExecutionStrategy {


    public CompilatorStrategy(IContainerService dockerContainerService, ISolutionAttemptRepository<SolutionAttemptRdbms, String> solutionAttemptRepository, ITaskRepository<Task, String> taskRepository, ITopicRepository<Topic, String> topicRepository, ITopicAccessService topicAccessService) {
        super(dockerContainerService, solutionAttemptRepository, taskRepository, topicRepository, topicAccessService);
    }
}
