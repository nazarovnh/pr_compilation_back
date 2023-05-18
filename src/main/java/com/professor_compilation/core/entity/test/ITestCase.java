package com.professor_compilation.core.entity.test;

public interface ITestCase {

    String getTaskId();

    String getTestCaseId();

    String getInput();

    String getCorrectOutput();

    void setTaskId(String taskId);

    void setTestCaseId(String testCaseId);

    void setInput(String input);

    void setCorrectOutput(String correctOutput);
}
