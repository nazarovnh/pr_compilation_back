package com.professor_compilation.utils.parameter;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.lang.reflect.Field;

/**
 * class ParameterUtils
 */
public class ParameterUtils {
    /**
     * This method retrieves the parameters from the provided object and maps them to a MapSqlParameterSource object.
     * It iterates over the fields of the object using reflection and sets the field values as named parameters in the MapSqlParameterSource object.
     * It uses for working with NamedParameterJdbcTemplate in repositories .
     * Example: getParameters
     * public class User {
     *     private String name;
     *     private int age;
     *     // Getters and setters...
     * }
     * MapSqlParameterSource params = ParameterUtils.getParameters(user);
     *
     * @param object - The object from which the parameters will be retrieved.
     * @return MapSqlParameterSource object containing the mapped parameters.
     * @param <T> Class of object
     */
    public static <T> MapSqlParameterSource getParameters(T object) {
        Class<?> clazz = object.getClass();
        MapSqlParameterSource params = new MapSqlParameterSource();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                params.addValue(field.getName(), value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error getting field value for " + field.getName(), e);
            }
        }
        return params;
    }


}
