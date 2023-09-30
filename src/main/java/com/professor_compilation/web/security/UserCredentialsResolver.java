package com.professor_compilation.web.security;

import com.professor_compilation.web.model.security.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolves {@link UserCredentials} method arguments
 */
public class UserCredentialsResolver implements HandlerMethodArgumentResolver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Whether the given method parameter is supported by this resolver.
     * @param parameter - MethodParameter
     * @return true if supported, false else
     */
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserCredentials.class);
    }

    /**
     * Resolves a method parameter into an argument value from a given request.
     * @param parameter - MethodParameter
     * @param mavContainer - ModelAndViewContainer
     * @param webRequest - request
     * @param binderFactory - factory for creating a WebDataBinder.
     * @return class UserCredentials
     */
    @Override
    public UserCredentials resolveArgument(
            @NonNull final  MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            @NonNull final  NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        try {
            logger.debug("Getting UserCredentials from request attribute");
            return (UserCredentials) webRequest.getAttribute(
                    JwtAuthInterceptor.getUserCredentials(), RequestAttributes.SCOPE_REQUEST
            );
        } catch (Exception e) {
            return null;
        }
    }

}

