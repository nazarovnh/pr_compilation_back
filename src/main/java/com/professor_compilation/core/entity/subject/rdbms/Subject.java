package com.professor_compilation.core.entity.subject.rdbms;

import com.professor_compilation.core.entity.subject.ISubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Subject  {
    private String subjectId;
    private Integer numberHours;
    private String subjectTitle;
    private String subjectDescription;

    public Subject() {

    }
}
