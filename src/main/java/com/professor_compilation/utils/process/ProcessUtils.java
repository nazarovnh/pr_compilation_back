package com.professor_compilation.utils.process;

import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.utils.process.model.ProcessResult;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * class ProcessUtils
 */
@Slf4j
public class ProcessUtils {
    /**
     * The executeCommand method allows you to execute an external command or process specified by the commands array.
     * It starts a new process using the provided commands and waits for it to complete within the specified timeout duration.
     * The method captures the standard output and standard error streams of the process and returns the result as a ProcessResult object.
     *
     * @param commands - An array of strings representing the command and its arguments to be executed.
     * @param timeout  - The maximum time in milliseconds to wait for the process to complete.
     *                 If the process does not finish within the specified timeout, it will be terminated.
     * @return An object that encapsulates the result of the process execution, including the standard output, standard error, and the time taken to execute the command.
     * @throws ProcessException - If an error occurs during the execution of the process or if the process exceeds the specified timeout
     */
    public static ProcessResult executeCommand(final String[] commands, Integer timeout) throws ProcessException {
        String stdOutRes;
        String stdError;
        Long executedTime = null;
        try {
            ProcessBuilder processbuilder = new ProcessBuilder(commands);
            Long startTime = System.currentTimeMillis();
            Process process = processbuilder.start();


            // Wait until the process terminated or the specified waiting time elapses.
            process.waitFor(timeout, TimeUnit.MILLISECONDS);

            Long endTime = System.currentTimeMillis();

            executedTime = endTime - startTime;

            // Did process terminate?
            if (process.isAlive()) {
                process.destroy();
                throw new ProcessException(String.valueOf(timeout));
            } else {
                stdOutRes = readOutput(process.getInputStream());
                stdError = readOutput(process.getErrorStream());
            }
        } catch (Exception exception) {
            throw new ProcessException("Process with commads " + Arrays.toString(commands) + " failed: " + exception.getMessage());
        }
        return new ProcessResult(stdOutRes, stdError, executedTime);
    }

    /**
     * The readOutput method reads the content from an InputStream and returns it as a String.
     * It reads the input streamline by line using a BufferedReader and appends each line to a StringBuilder along with the system-dependent
     * line separator.
     * Finally, it returns the accumulated content as a single String.
     * @param inputStream: The input stream from which to read the content.
     * @return String: The content read from the input stream as a single string.
     * @throws IOException: If an I/O error occurs while reading the input stream.
     */

    public static String readOutput(InputStream inputStream) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
            return builder.toString();
        }
    }
}
