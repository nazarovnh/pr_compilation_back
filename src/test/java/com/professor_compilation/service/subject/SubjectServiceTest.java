package com.professor_compilation.service.subject;

import com.professor_compilation.core.entity.group.StudyGroupRdbms;
import com.professor_compilation.core.entity.solution.rdbms.SolutionAttemptRdbms;
import com.professor_compilation.core.entity.subject.rdbms.Subject;
import com.professor_compilation.core.entity.task.rdbms.Task;
import com.professor_compilation.core.entity.topic.access.rdbms.TopicAccessRdbms;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.repository.group.IStudyGroupRepository;
import com.professor_compilation.core.repository.solution.ISolutionAttemptRepository;
import com.professor_compilation.core.repository.subject.ISubjectRepository;
import com.professor_compilation.core.repository.task.ITaskRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.repository.topic.access.ITopicAccessRepository;
import com.professor_compilation.core.service.subject.ISubjectService;
import com.professor_compilation.core.service.subject.SubjectService;
import com.professor_compilation.web.model.subject.response.SubjectGetResponse;
import com.professor_compilation.web.model.topic.response.TopicSubjectGetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SubjectServiceTest {
    @Mock
    private ISubjectRepository<Subject, String> subjectRepository;
    @Mock
    private ITopicAccessRepository<TopicAccessRdbms, String> topicAccessRepository;
    @Mock
    private IStudyGroupRepository<StudyGroupRdbms, String> studyGroupRepository;
    @Mock
    private ITopicRepository<Topic, String> topicRepository;
    @Mock
    private ISolutionAttemptRepository<SolutionAttemptRdbms, String> solutionAttemptRepository;
    @Mock
    private ITaskRepository<Task, String> taskRepository;

    ModelMapper modelMapper = new ModelMapper();

    private SubjectService subjectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        subjectService = new SubjectService(
                subjectRepository,
                topicAccessRepository,
                studyGroupRepository,
                topicRepository,
                taskRepository,
                solutionAttemptRepository,
                modelMapper
        );
    }

        @Test
        public void getSubjectTopicsById_WithCorrectSubjectId_ShouldReturnSubjectGetResponse() {
        String subjectId = "testSubjectId";
        String userId = "testUserId";
        Subject subjectInfo = new Subject(subjectId, 50, "Test Subject", "Description of test subject");


        List<Topic> topics = Arrays.asList(
                new Topic("topicId1", subjectId, "Topic 1", "Description 1", 1, 80),
                new Topic("topicId2", subjectId, "Topic 2", "Description 2", 2, 75)
        );

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subjectInfo));
        when(topicRepository.getTopicsBySubjectId(subjectId)).thenReturn(topics);


        when(taskRepository.findByTopicId(anyString())).thenReturn(Arrays.asList(
                new Task(UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        "taskTitle",
                        "taskDescription",
                        1,
                        1,
                        1,
                        1
                )));
        when(solutionAttemptRepository.isAttemptStatusSuccess(anyString(), anyString())).thenReturn(true);

        SubjectGetResponse response = subjectService.getSubjectTopicsById(subjectId, userId);

        assertEquals(subjectInfo.getSubjectTitle(), response.getSubjectTitle());
        assertEquals(topics.size(), response.getTopics().size());

        verify(subjectRepository, times(1)).findById(subjectId);
        verify(topicRepository, times(1)).getTopicsBySubjectId(subjectId);
        verify(taskRepository, times(topics.size())).findByTopicId(anyString());
        verify(solutionAttemptRepository, times(topics.size())).isAttemptStatusSuccess(anyString(), anyString());
    }


}
