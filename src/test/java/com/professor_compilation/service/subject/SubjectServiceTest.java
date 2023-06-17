package com.professor_compilation.service.subject;

import com.professor_compilation.core.entity.group.StudyGroupRdbms;
import com.professor_compilation.core.entity.subject.rdbms.Subject;
import com.professor_compilation.core.entity.topic.access.rdbms.TopicAccessRdbms;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.repository.group.IStudyGroupRepository;
import com.professor_compilation.core.repository.subject.ISubjectRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.repository.topic.access.ITopicAccessRepository;
import com.professor_compilation.core.service.subject.ISubjectService;
import com.professor_compilation.core.service.subject.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.mock;

public class SubjectServiceTest {
    private ISubjectRepository<Subject, String> subjectRepository;
    private ITopicAccessRepository<TopicAccessRdbms, String> topicAccessRepository;
    private IStudyGroupRepository<StudyGroupRdbms, String> studyGroupRepository;
    private ITopicRepository<Topic, String> topicRepository;
    private ModelMapper modelMapper;
    private ISubjectService subjectService;

    @BeforeEach
    public void setUp() {
        subjectRepository = mock(ISubjectRepository.class);
        topicAccessRepository = mock(ITopicAccessRepository.class);
        studyGroupRepository = mock(IStudyGroupRepository.class);
        topicRepository = mock(ITopicRepository.class);
        modelMapper = mock(ModelMapper.class);
        subjectService = new SubjectService(subjectRepository, topicAccessRepository, studyGroupRepository,
                topicRepository, modelMapper);
    }

    @Test
    public void getSubjectTopicsById_WithCorrectSubjectId_ShouldReturnSubjectGetResponse() {
//        String roomId = UUID.randomUUID().toString();
//        String playerId = UUID.randomUUID().toString();
//        UserCredentials mockUserCredentials = mock(UserCredentials.class);
//        List<String> listIdQuestions = mock(List.class);
//        List<String> listIdPlayers = mock(List.class);
//        String questionId = UUID.randomUUID().toString();
//        QuestionLocation mockQuestionLocation = mock(QuestionLocation.class);
//
//        when(mockUserCredentials.getUserId()).thenReturn(playerId);
//        when(mockRoomRepository.getOwnerIdByRoomId(roomId)).thenReturn(playerId);
//        when(mockRoomRepository.getIdPlayers(roomId)).thenReturn(listIdPlayers);
//        when(listIdPlayers.contains(playerId)).thenReturn(true);
//        when(mockQuestionRepository.getQuestionsIdStartGame()).thenReturn(listIdQuestions);
//        doNothing().when(mockGameRepository).createGame(roomId, listIdQuestions, listIdPlayers);
//        when(mockGameRepository.getCurrentQuestionId(roomId)).thenReturn(questionId);
//        when(mockQuestionLocation.getQuestionId()).thenReturn(questionId);
//
//        QuestionLocation result = gameService.startGame(roomId, mockUserCredentials);
//
//
//        verify(mockUserCredentials, times(2)).getUserId();
//        verify(mockRoomRepository, times(2)).getIdPlayers(roomId);
//        verify(listIdPlayers, times(1)).contains(playerId);
//        verify(mockQuestionRepository, times(1)).getQuestionsIdStartGame();
//        verify(mockGameRepository, times(1)).createGame(roomId, listIdQuestions, listIdPlayers);
//        verify(mockGameRepository, times(1)).getCurrentQuestionId(roomId);
//
//        assertEquals(mockQuestionLocation.getQuestionId(), result.getQuestionId());
    }


}
