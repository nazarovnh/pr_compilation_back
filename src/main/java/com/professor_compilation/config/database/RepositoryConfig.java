package com.professor_compilation.config.database;

import com.professor_compilation.core.repository.group.IStudyGroupRepository;
import com.professor_compilation.core.repository.group.StudyGroupRepository;
import com.professor_compilation.core.repository.solution.ISolutionAttemptRepository;
import com.professor_compilation.core.repository.solution.SolutionAttemptRepository;
import com.professor_compilation.core.repository.subject.ISubjectRepository;
import com.professor_compilation.core.repository.subject.SubjectRepository;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.task.TaskRepository;
import com.professor_compilation.core.repository.test.ITestRepository;
import com.professor_compilation.core.repository.test.TestRepository;
import com.professor_compilation.core.repository.token.ITokenRepository;
import com.professor_compilation.core.repository.token.TokenRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.repository.topic.TopicRepository;
import com.professor_compilation.core.repository.topic.access.ITopicAccessRepository;
import com.professor_compilation.core.repository.topic.access.TopicAccessRepository;
import com.professor_compilation.core.repository.user.IUserRepository;
import com.professor_compilation.core.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryConfig {
    @Bean
    public ITestRepository testRepository(final NamedParameterJdbcOperations jdbcOperations) {
        return new TestRepository(jdbcOperations);
    }

    @Bean
    public ITaskRepository taskRepository(final NamedParameterJdbcOperations jdbcOperations) {
        return new TaskRepository(jdbcOperations);
    }

    @Bean
    public ITokenRepository tokenRepository(final JdbcOperations jdbcOperations) {
        return new TokenRepository(jdbcOperations);
    }

    @Bean
    public IUserRepository userRepository(final JdbcOperations jdbcOperations) {
        return new UserRepository(jdbcOperations);
    }

    @Bean
    public ISubjectRepository subjectRepository(final NamedParameterJdbcOperations jdbcOperations) {
        return new SubjectRepository(jdbcOperations);
    }

    @Bean
    public ITopicRepository topicRepository(final NamedParameterJdbcOperations jdbcOperations) {
        return new TopicRepository(jdbcOperations);
    }

    @Bean
    public ITopicAccessRepository topicAccessRepository(final NamedParameterJdbcOperations jdbcOperations) {
        return new TopicAccessRepository(jdbcOperations);
    }

    @Bean
    public ISolutionAttemptRepository solutionAttemptRepository(final NamedParameterJdbcOperations jdbcOperations) {
        return new SolutionAttemptRepository(jdbcOperations);
    }

    @Bean
    public IStudyGroupRepository studyGroupRepository(final NamedParameterJdbcOperations jdbcOperations) {
        return new StudyGroupRepository(jdbcOperations);
    }

}
