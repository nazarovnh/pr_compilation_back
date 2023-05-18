package com.professor_compilation.utils.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * class ProcessResult
 */
@AllArgsConstructor
@Data
public class ProcessResult {
    private String outputRes;
    private String outputErr;
    private Long executedTime;
}
