package com.professor_compilation.core.entity.subject.rdbms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectStudyGroupRdbms {
    private String subjectId;
    private String studyGroupId;

}
