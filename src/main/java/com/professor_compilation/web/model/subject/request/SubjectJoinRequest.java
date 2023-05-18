package com.professor_compilation.web.model.subject.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SubjectJoinRequest {
    private final String studyGroupId;

    @JsonCreator
    public SubjectJoinRequest(@JsonProperty("studyGroupId") final String studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

}
