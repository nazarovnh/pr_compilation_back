package com.professor_compilation.core.service.subject;

import com.professor_compilation.core.entity.group.StudyGroupRdbms;
import com.professor_compilation.core.entity.subject.rdbms.Subject;
import com.professor_compilation.core.entity.topic.access.rdbms.TopicAccessRdbms;
import com.professor_compilation.core.entity.topic.rdbms.Topic;
import com.professor_compilation.core.repository.group.IStudyGroupRepository;
import com.professor_compilation.core.repository.subject.ISubjectRepository;
import com.professor_compilation.core.repository.topic.ITopicRepository;
import com.professor_compilation.core.repository.topic.access.ITopicAccessRepository;
import com.professor_compilation.utils.convert.ConvertUtils;
import com.professor_compilation.web.model.subject.request.SubjectCreateRequest;
import com.professor_compilation.web.model.subject.request.SubjectJoinRequest;
import com.professor_compilation.web.model.subject.request.SubjectPatchRequest;
import com.professor_compilation.web.model.subject.response.SubjectCreateResponse;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SubjectService implements ISubjectService {
    private final ISubjectRepository<Subject, String> subjectRepository;
    private final ITopicAccessRepository<TopicAccessRdbms, String> topicAccessRepository;
    private final IStudyGroupRepository<StudyGroupRdbms, String> studyGroupRepository;
    private final ITopicRepository<Topic, String> topicRepository;


    @Override
    public SubjectCreateResponse createSubject(SubjectCreateRequest subjectCreateRequest) {
        Subject subject = subjectRepository.save(ConvertUtils.convert(subjectCreateRequest, Subject.class));
        SubjectCreateResponse subjectCreateResponse = ConvertUtils.convert(subject, SubjectCreateResponse.class);
        return subjectCreateResponse;
    }

    @Override
    public Subject getSubjectById(String subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }

    @Override
    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjectRepository.findAll());
    }

    @Override
    public Subject patchSubjectById(String subjectId, SubjectPatchRequest subjectPatchRequest) {
        Subject subject = ConvertUtils.convert(subjectPatchRequest, Subject.class);
        subject.setSubjectId(subjectId);
        return subjectRepository.update(subject);
    }

    @Override
    public void deleteSubjectById(String subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public void joinSubject(String subjectId, SubjectJoinRequest subject) {
        subjectRepository.saveStudyGroup(subjectId, subject.getStudyGroupId());
        List<String> studentIds = studyGroupRepository.findAllByStudyGroupId(subject.getStudyGroupId()).stream().map(StudyGroupRdbms::getUserId).collect(Collectors.toList());
        List<String> topicIds = topicRepository.getTopicsBySubjectId(subjectId).stream()
                .map(Topic::getTopicId)
                .collect(Collectors.toList());
        for (String studentId :
                studentIds) {
            for (String topicId :
                    topicIds) {
                topicAccessRepository.initAccess(topicId, studentId, Objects.equals(topicIds.get(0), topicId));
            }
        }
    }

}
