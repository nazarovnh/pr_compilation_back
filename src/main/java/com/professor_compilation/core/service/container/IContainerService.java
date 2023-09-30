package com.professor_compilation.core.service.container;

import com.professor_compilation.core.entity.test.ITestCase;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.test.TestCase;
import com.professor_compilation.utils.process.model.ProcessResult;

/**
 * IContainerService
 */
public interface IContainerService {

    /**
     * Method build image by execution model
     * @param execution - execution model
     * @return result of building image
     * @throws ProcessException -ProcessException
     */
    ProcessResult buildImage(final Execution execution) throws ProcessException;

    /**
     * Run user solution in container for a specific test case
     * @param imageName - name of image
     * @param execution - execution model
     * @param testCase - test case
     * @return result of running container
     * @throws ProcessException - ProcessException
     */
    ProcessResult runContainer(final String imageName, final Execution execution, final TestCase testCase) throws ProcessException;

    /**
     * Remove image by name
     * @param imageName - name of image
     * @return result of removing image
     * @throws ProcessException - ProcessException
     */
    ProcessResult removedImage(final String imageName) throws ProcessException;
}
