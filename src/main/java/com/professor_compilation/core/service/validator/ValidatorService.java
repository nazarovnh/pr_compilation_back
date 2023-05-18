package com.professor_compilation.core.service.validator;

import com.professor_compilation.core.model.exception.user.UserException;
import com.professor_compilation.core.model.exception.validator.ValidatorException;
import org.springframework.http.HttpStatus;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class ValidatorService implements IValidatorService {
    @Override
    public <T> T validateExistenceEntity(final Optional<T> data, final String exceptionMessage,
                                         final Class<? extends RuntimeException> exceptionClass) {
        if (data.isPresent()) {
            return data.get();
        } else {
            try {
                throw exceptionClass.getDeclaredConstructor(String.class, HttpStatus.class).newInstance(exceptionMessage, HttpStatus.NOT_FOUND);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException ex) {
                throw new ValidatorException("Failed to create exception instance", ex);
            }
        }
    }


}
