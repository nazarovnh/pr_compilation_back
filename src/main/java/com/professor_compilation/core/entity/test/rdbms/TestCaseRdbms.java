package com.professor_compilation.core.entity.test.rdbms;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TestCaseRdbms {
    private String testCaseId;
    private String taskId;
    private String input;
    private String correctOutput;
}
