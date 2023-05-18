package com.professor_compilation.utils.rowmapper;

import com.google.common.base.CaseFormat;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The EntityRowMapper class is an implementation of the RowMapper interface from the Spring Framework.
 * It provides functionality for mapping rows from a ResultSet to instances of a specific entity class (T).
 * It uses reflection to instantiate the entity class and set its field values based on the column names in the ResultSet.
 * @param <T>
 */
public class EntityRowMapper<T> implements RowMapper<T> {

    private final Class<T> entityClass;
    private final Map<String, Field> fieldMap;

    public EntityRowMapper(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.fieldMap = getFieldMap(entityClass);
    }

    /**
     * The mapRow method is responsible for mapping a row from the ResultSet to an instance of the entity class (T).
     * It creates a new instance of the entity class using reflection and sets its field values based on the column names in the ResultSet.
     * The method iterates over the entries in the fieldMap, retrieves the column name and corresponding Field object,
     * and obtains the value from the ResultSet using the column name and field type.
     * If the value is not null, it sets the field value in the entity object.
     * @param rs The ResultSet object containing the row to be mapped.
     * @param rowNum The current row number.
     * @return An instance of the entity class (T) with its field values set based on the row from the ResultSet.
     * @throws SQLException - SQLException
     */
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T entity;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Failed to instantiate entity class", e);
        }
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            String columnName = entry.getKey();
            Field field = entry.getValue();
            Object value = rs.getObject(columnName, field.getType());
            if (value != null) {
                try {
                    field.setAccessible(true);
                    field.set(entity, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to set field value", e);
                }
            }
        }
        return entity;
    }

    /**
     * Returns a map of field names to Field objects for the given entity class.
     * @param entityClass - entity
     * @return a map of field names to Field objects for the given entity class.
     * @param <T> - class of entity
     */
    private static <T> Map<String, Field> getFieldMap(Class<T> entityClass) {
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : entityClass.getDeclaredFields()) {
            String columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            fieldMap.put(columnName, field);
        }
        return fieldMap;
    }

    /**
     * Returns a new instance of EntityRowMapper for the specified entity class
     * @param clazz - entity
     * @return a new instance of EntityRowMapper for the specified entity class
     * @param <T> class
     */
    public static <T> RowMapper<T> getEntityRowMapper(Class<T> clazz) {
        return new EntityRowMapper<>(clazz);
    }

}
