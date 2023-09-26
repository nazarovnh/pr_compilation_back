package com.professor_compilation.config.service;

import com.professor_compilation.core.entity.group.StudyGroupRdbms;
import com.professor_compilation.core.entity.solution.rdbms.SolutionAttemptRdbms;
import com.professor_compilation.core.entity.subject.rdbms.Subject;
import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.topic.access.rdbms.TopicAccessRdbms;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.core.model.token.RefreshToken;
import com.professor_compilation.core.repository.group.IStudyGroupRepository;
import com.professor_compilation.core.repository.solution.ISolutionAttemptRepository;
import com.professor_compilation.core.repository.subject.ISubjectRepository;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.token.ITokenRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.repository.topic.access.ITopicAccessRepository;
import com.professor_compilation.core.repository.user.IUserRepository;
import com.professor_compilation.core.security.PasswordEncoder;
import com.professor_compilation.core.service.container.DockerContainerService;
import com.professor_compilation.core.service.container.IContainerService;
import com.professor_compilation.core.service.execution.ExecutionService;
import com.professor_compilation.core.service.execution.factory.ExecutionFactory;
import com.professor_compilation.core.service.login.ILoginService;
import com.professor_compilation.core.service.login.LoginService;
import com.professor_compilation.core.service.signup.ISignUpService;
import com.professor_compilation.core.service.signup.SignUpService;
import com.professor_compilation.core.service.strategy.ExecutionStrategy;
import com.professor_compilation.core.service.strategy.compilator.CompilatorStrategy;
import com.professor_compilation.core.service.strategy.interpreter.InterpreterStrategy;
import com.professor_compilation.core.service.subject.ISubjectService;
import com.professor_compilation.core.service.subject.SubjectService;
import com.professor_compilation.core.service.token.ITokenService;
import com.professor_compilation.core.service.token.TokenService;
import com.professor_compilation.core.service.token.jwt.JwtTokenService;
import com.professor_compilation.core.service.topic.ITopicService;
import com.professor_compilation.core.service.topic.TopicService;
import com.professor_compilation.core.service.topic.access.ITopicAccessService;
import com.professor_compilation.core.service.topic.access.TopicAccessService;
import com.professor_compilation.core.service.validator.IValidatorService;
import com.professor_compilation.core.service.validator.ValidatorService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    /**
     * Bean of the ITokenService
     *
     * @param jwtTokenService - service of jwttoken
     * @param tokenRepository - repository of token
     * @param userRepository  - repository of user
     * @return realization of ITokenService
     */
    @Bean
    public ITokenService tokenService(final JwtTokenService jwtTokenService,
                                      final ITokenRepository tokenRepository,
                                      final IUserRepository userRepository,
                                      final IValidatorService validatorService) {
        return new TokenService(jwtTokenService, tokenRepository, userRepository, validatorService);
    }

    @Bean
    public InterpreterStrategy interpreterStrategy(final IContainerService dockerContainerService, final ISolutionAttemptRepository solutionAttemptRepository,
                                                   final ITaskRepository taskRepository, final ITopicRepository<Topic, String> topicRepository, final ITopicAccessService topicAccessService) {
        return new InterpreterStrategy(dockerContainerService, solutionAttemptRepository, taskRepository, topicRepository, topicAccessService);
    }

    @Bean
    public CompilatorStrategy compilatorStrategy(final IContainerService dockerContainerService, final ISolutionAttemptRepository solutionAttemptRepository,
                                                 final ITaskRepository taskRepository, final ITopicRepository<Topic, String> topicRepository, final ITopicAccessService topicAccessService) {
        return new CompilatorStrategy(dockerContainerService, solutionAttemptRepository, taskRepository, topicRepository, topicAccessService);
    }

    @Bean
    public ITopicAccessService topicAccessService(final ITopicRepository<Topic, String> topicRepository,
                                                  final ITopicAccessRepository<TopicAccessRdbms, String> topicAccessRepository) {
        return new TopicAccessService(topicRepository, topicAccessRepository);
    }


    /**
     * Bean of the LoginService
     *
     * @param userRepository  - repository of user
     * @param passwordEncoder - encoder of password
     * @return realization of ILoginService
     */
    @Bean
    public ILoginService loginService(final IUserRepository userRepository,
                                      final PasswordEncoder passwordEncoder,
                                      final IValidatorService validatorService) {
        return new LoginService(userRepository, passwordEncoder, validatorService);
    }

    @Bean
    public ExecutionService executionService(ExecutionFactory executionFactory, ExecutionStrategy compilatorStrategy, ExecutionStrategy interpreterStrategy) {
        return new ExecutionService(executionFactory, compilatorStrategy, interpreterStrategy);
    }

    @Bean
    public IContainerService dockerContainerService() {
        return new DockerContainerService();
    }

    /**
     * Bean of the SignUpService
     *
     * @param userRepository  - repository of user
     * @param passwordEncoder - encoder of password
     * @return realization of ISignUpService
     */
    @Bean
    public ISignUpService signUpService(final IUserRepository<User, String> userRepository,
                                        final ITokenRepository<RefreshToken, String> tokenRepository,
                                        final PasswordEncoder passwordEncoder) {
        return new SignUpService(userRepository, tokenRepository, passwordEncoder);
    }

    @Bean
    public IValidatorService validatorService() {
        return new ValidatorService();
    }

    @Bean
    public ISubjectService subjectService(final ISubjectRepository<Subject, String> subjectRepository, final ITopicAccessRepository<TopicAccessRdbms, String> topicAccessRepository,
                                          final IStudyGroupRepository<StudyGroupRdbms, String> studyGroupRepository,
                                          final ITopicRepository<Topic, String> topicRepository,
                                          final ITaskRepository<Task, String> taskRepository,
                                          final ISolutionAttemptRepository<SolutionAttemptRdbms, String> solutionAttemptRepository,
                                          final ModelMapper modelMapper) {
        return new SubjectService(subjectRepository, topicAccessRepository, studyGroupRepository,
                topicRepository, taskRepository, solutionAttemptRepository, modelMapper);
    }

    @Bean
    public ITopicService topicService(final ITopicRepository<Topic, String> topicRepository, final ITaskRepository<Task, String> taskRepository,
                                      final ModelMapper modelMapper) {
        return new TopicService(topicRepository, taskRepository, modelMapper);
    }
}
