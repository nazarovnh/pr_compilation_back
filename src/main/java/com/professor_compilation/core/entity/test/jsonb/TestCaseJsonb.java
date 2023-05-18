package com.professor_compilation.core.entity.test.jsonb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.professor_compilation.core.entity.test.ITestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
public class TestCaseJsonb implements ITestCase {
    @JsonProperty("task_id")
    private String taskId;
    @JsonProperty("test_case_id")
    private String testCaseId;
    @JsonProperty("input")
    private String input;
    @JsonProperty("correct_output")
    private String correctOutput;



    public Map<String, Object> toMap() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Map.class);
    }

    public static TestCaseJsonb fromMap(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, TestCaseJsonb.class);
    }
}
