package com.professor_compilation.core.service.subject;

import com.professor_compilation.core.entity.subject.rdbms.Subject;
import com.professor_compilation.web.model.subject.request.SubjectCreateRequest;
import com.professor_compilation.web.model.subject.request.SubjectJoinRequest;
import com.professor_compilation.web.model.subject.request.SubjectPatchRequest;
import com.professor_compilation.web.model.subject.response.SubjectCreateResponse;
import com.professor_compilation.web.model.subject.response.SubjectGetResponse;

import java.util.List;

public interface ISubjectService {
    SubjectCreateResponse createSubject(SubjectCreateRequest subjectCreateRequest);
    Subject getSubjectById(final String subjectId);

    SubjectGetResponse getSubjectTopicsById(String subjectId, String userId);

    List<Subject> getAllSubjects();
    Subject patchSubjectById(final String subjectId, final SubjectPatchRequest subjectPatchRequest);
    void deleteSubjectById(final String subjectId);

    /**
     * Method for join study group to subject
     * @param subjectId - id of subject
     * @param subject - subject request
     */
    void joinSubject(String subjectId, SubjectJoinRequest subject);
}
