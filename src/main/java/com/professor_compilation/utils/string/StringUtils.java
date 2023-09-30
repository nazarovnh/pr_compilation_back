package com.professor_compilation.utils.string;

import com.professor_compilation.utils.process.model.ProcessResult;

/**
 * class StringUtils
 */
public class StringUtils {

    public static void removeLineSeparatorProcessResult(ProcessResult processResult) {
        processResult.setOutputRes(processResult.getOutputRes().replace(System.getProperty("line.separator"), "").trim());
        processResult.setOutputErr(processResult.getOutputErr().replace(System.getProperty("line.separator"), "").trim());
    }
}
