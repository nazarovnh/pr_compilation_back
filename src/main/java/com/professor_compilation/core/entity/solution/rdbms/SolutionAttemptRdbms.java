package com.professor_compilation.core.entity.solution.rdbms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionAttemptRdbms {
    private String attemptId;
    private String userId;
    private String taskId;
    private String filename;
    private byte[] fileData;
    private Integer attemptScore;
    private Long executionTime;
    private String programmingLanguage;
    private String attemptStatus;
}
