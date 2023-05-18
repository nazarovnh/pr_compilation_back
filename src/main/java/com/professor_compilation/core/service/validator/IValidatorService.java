package com.professor_compilation.core.service.validator;

import java.util.Optional;

/**
 * interface IValidatorService
 */
public interface IValidatorService {
    <T> T validateExistenceEntity(final Optional<T> data, final String exceptionMessage,
                                  final Class<? extends RuntimeException> exceptionClass);
}
