package com.professor_compilation.core.entity.subject.rdbms;

import com.professor_compilation.core.entity.subject.ISubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Subject  {
    private String subjectId;
    private Integer numberHours;
    private String subjectTitle;
    private String subjectDescription;

    public Subject() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectId, subject.subjectId) && Objects.equals(numberHours, subject.numberHours) && Objects.equals(subjectTitle, subject.subjectTitle) && Objects.equals(subjectDescription, subject.subjectDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, numberHours, subjectTitle, subjectDescription);
    }
}
