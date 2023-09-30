package com.professor_compilation.web.model.subject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCreateResponse {
    private  String subjectId ;
    private  Integer numberHours;
    private  String subjectTitle;
    private  String subjectDescription;

}
