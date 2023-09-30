package com.professor_compilation.web.model.topic.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TopicPatchRequest {
    @JsonProperty("subjectId")
    private final String subjectId;
    @JsonProperty("title")
    private final String title;
    @JsonProperty("description")
    private final String description;
    @JsonProperty("order")
    private final Integer order;
    @JsonProperty("thresholdScore")
    private final Integer thresholdScore;
}
