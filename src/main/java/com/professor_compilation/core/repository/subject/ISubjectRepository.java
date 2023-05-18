package com.professor_compilation.core.repository.subject;

import com.professor_compilation.core.model.exception.subject.SubjectException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ISubjectRepository <T, ID extends Serializable> {

    Optional<T> findById(final ID subjectId);

    Collection<T> findAll();

    T save(final T subject);

    void deleteById(final ID subjectId);

    T update(final T subject);

    void saveStudyGroup(ID subjectId, ID studyGroupId);

}