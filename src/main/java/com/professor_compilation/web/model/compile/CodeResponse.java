package com.professor_compilation.web.model.compile;

import com.professor_compilation.core.model.test.TestCaseResult;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CodeResponse {
    private String result;
    private Integer resultStatus;
    private List<TestCaseResult> testCaseResultList;
    private Long executedTime;
}
