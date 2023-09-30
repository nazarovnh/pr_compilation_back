package com.professor_compilation.constants;

import lombok.Data;

@Data
public class Constants {
    // Name of entity
    public static final String SUBJECT = "SUBJECT";
    public static final String TOPIC = "TOPIC";
    public static final String TASK = "TASK";
    public static final String TESTCASE = "TESTCASE";
    public static final String SOLUTION_ATTEMPT = "SOLUTION_ATTEMPT";
    public static final String TOPIC_ACCESS = "TOPIC_ACCESS";

    // Status attempt
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    // Result
    public static final String resultSuccess = "OK";
    public static final String resultIncorrectSolution = "Uncorrected result";

    // Status of result
    public static final Integer statusSuccess = 200;
    public static final Integer statusIncorrectSolution = 400;


    // Error execption

    public static String getMessageNoSuchEntity(String entityName, String id) {
        return String.format("%s WITH ID - %s WASN'T FOUND", entityName, id);
    }
}
