package com.professor_compilation.core.model.test;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TestCase {
    private String input;
    private String correctOutput;
}
