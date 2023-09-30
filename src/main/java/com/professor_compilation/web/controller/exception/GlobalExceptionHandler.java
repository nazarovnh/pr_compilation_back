package com.professor_compilation.web.controller.exception;

import com.professor_compilation.core.model.exception.ErrorResponse;
import com.professor_compilation.core.model.exception.NoSuchElementFoundException;
import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.login.LoginFailedException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class ExceptionsHandler
 * Class handlers exception in system and sends response to client
 */
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String TRACE = "trace";

    @Value("${reflectoring.trace:false}")
    private Boolean printStackTrace;

    /**
     * Handle exception with process execution
     *
     * @param exception - exception with not found entity
     * @return not found status with message
     */
    @ExceptionHandler(value = {ProcessException.class})
    public ResponseEntity<String> handleProcessException(final ProcessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle exception with environment exception
     *
     * @param exception - exception with not found entity
     * @return not found status with message
     */
    @ExceptionHandler(value = {EnvironmentException.class})
    public ResponseEntity<String> handleProcessException(final EnvironmentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle exception with not found case
     * @param itemNotFoundException - itemNotFoundException
     * @param request - WebRequest
     * @return response status not found
     */
    @ExceptionHandler(value = {NoSuchElementFoundException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException itemNotFoundException, WebRequest request) {
        log.error("Failed to find the requested element", itemNotFoundException);
        return buildErrorResponse(itemNotFoundException, itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Handle exception with login failed exception
     *
     * @param exception - exception LoginFailedException
     * @return response status of exception
     */
    @ExceptionHandler(value = {LoginFailedException.class})
    public ResponseEntity<String> handleProcessException(final LoginFailedException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getClass().getAnnotation(ResponseStatus.class).value());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error. Check 'errors' field for details.");
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    /**
     * Handle exception which were not processed in the code
     * @param exception - exception
     * @param request - WebRequest
     * @return response with internal sever error
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        log.error("Unknown error occurred", exception);
        return buildErrorResponse(exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }


    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      String message,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}