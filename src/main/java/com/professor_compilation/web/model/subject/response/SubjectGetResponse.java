package com.professor_compilation.web.model.subject.response;

import com.professor_compilation.web.model.topic.response.TopicSubjectGetResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Array;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SubjectGetResponse {
    private String subjectId;
    private String subjectTitle;
    private String subjectDescription;
    private Integer numberTasks;
    private List<String> languages;
    private List<TopicSubjectGetResponse> topics;
}
