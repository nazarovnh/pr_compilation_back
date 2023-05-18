package com.professor_compilation.core.annotations;



import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Documented;

/**
 * interface AuthRoleRequired.
 * Set annotation with role
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface AuthRoleRequired {
    /**
     * Returns a role which is required to access the method
     * @return the role name
     */
    String value();
}
