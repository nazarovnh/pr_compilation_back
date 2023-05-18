package com.professor_compilation.web.model.subject.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubjectCreateRequest {
    @JsonProperty("numberHours")
    private final Integer numberHours;

    @JsonProperty("subjectTitle")
    private final String subjectTitle;

    @JsonProperty("subjectDescription")
    private final String subjectDescription;
}
