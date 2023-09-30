package com.professor_compilation.utils.convert;

import org.modelmapper.ModelMapper;

/**
 * class ConvertUtils
 */
public class ConvertUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Method convert source class to destination class
     * @param source - source class
     * @param destinationType - destination class
     * @return destination class with data from source class
     * @param <T> - class of source
     * @param <U> - class of destination
     */
    public static <T, U> U convert(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
