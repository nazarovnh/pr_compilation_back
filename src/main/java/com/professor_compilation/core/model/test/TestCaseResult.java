package com.professor_compilation.core.model.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCaseResult {
    private String result;
    private Integer resultStatus;
    private String correctOutput;
    private String output;
    private Long timeExecuted;
}
