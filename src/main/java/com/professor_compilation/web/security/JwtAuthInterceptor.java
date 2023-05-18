package com.professor_compilation.web.security;
import com.professor_compilation.core.annotations.AuthRoleRequired;
import com.professor_compilation.core.service.token.jwt.JwtTokenService;
import com.professor_compilation.web.model.security.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring interceptor for JWT based authentication and authorization
 */
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String userCredentials = "userCredentialsAttr";

    private final Integer lengthIntroductoryWords = 7;

    private final JwtTokenService jwtService;

    /**
     * public constructor of JwtAuthInterceptor
     * @param jwtService - jwtService
     */
    public JwtAuthInterceptor(
            final JwtTokenService jwtService
    ) {
        this.jwtService = jwtService;
    }

    /**
     * Pre handle http request for rights verification
     * @param request - http request
     * @param response - http response
     * @param handler - handler of request
     * @return true if user has enough rights
     */
    @Override
    public boolean preHandle(@NonNull final HttpServletRequest request,
                             @NonNull final HttpServletResponse response,
                             @NonNull final Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            return checkAuthorization(method, request, response);
        }
        return true;
    }

    /**
     * Get credentials of user from jwt token
     * @param request - http request
     * @return credentials of user
     */
    private UserCredentials getUserCredentials(final HttpServletRequest request) {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith("Bearer ")) {
                return null;
            }

            String token = header.substring(lengthIntroductoryWords);
            UserCredentials credentials = jwtService.parseToken(token);
            logger.debug("Found credentials in Authorization header: {}", credentials.getUsername());
            request.setAttribute(userCredentials, credentials);
            return credentials;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checking user rights for request
     * @param method - http method
     * @param request - http request
     * @param response - http response
     * @return true if user gas enough rights
     */
    private boolean checkAuthorization(
            final Method method,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {
        try {
            UserCredentials userCredentials = getUserCredentials(request);

            if (method.isAnnotationPresent(AuthRoleRequired.class)) {
                if (userCredentials == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return false;
                }
                AuthRoleRequired annotation = method.getAnnotation(AuthRoleRequired.class);
                String requiredRole = annotation.value();
                Set<String> userRoles = new HashSet<>();
                if (userCredentials.getRoles() != null) {
                    userRoles.addAll(userCredentials.getRoles());
                }
                boolean isAuthorized = userRoles.contains(requiredRole);
                if (!isAuthorized) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not enough permissions");
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get USER_CREDENTIALS
     * @return USER_CREDENTIALS
     */
    public static String getUserCredentials() {
        return userCredentials;
    }
}
