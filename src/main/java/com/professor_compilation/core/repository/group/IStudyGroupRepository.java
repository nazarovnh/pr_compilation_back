package com.professor_compilation.core.repository.group;

import java.io.Serializable;
import java.util.List;

public interface IStudyGroupRepository<T, ID extends Serializable> {
    List<T> findAllByStudyGroupId(ID studyGroupId);
}
