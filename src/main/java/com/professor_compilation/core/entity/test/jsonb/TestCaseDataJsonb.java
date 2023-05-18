package com.professor_compilation.core.entity.test.jsonb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class TestCaseDataJsonb {
    @JsonProperty("input")
    private String input;
    @JsonProperty("correct_output")
    private String correctOutput;

    public Map<String, Object> toMap() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Map.class);
    }

    public static TestCaseDataJsonb fromMap(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, TestCaseDataJsonb.class);
    }
}
