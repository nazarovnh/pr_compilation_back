package com.professor_compilation.core.model.task;

import com.professor_compilation.core.entity.test.rdbms.TestCaseRdbms;
import com.professor_compilation.core.model.language.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 *  The TaskInfo class represents information about a task,
 *  including its associated topic, task ID, time limit, memory limit, test cases, and programming language.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfo {
    String topicId;
    String taskId;
    Integer timeLimit;
    Integer memoryLimit;
    Collection<TestCaseRdbms> testCaseEntity;
    Language language;
}
