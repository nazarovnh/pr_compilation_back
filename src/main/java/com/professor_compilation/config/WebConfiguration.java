package com.professor_compilation.config;

import com.professor_compilation.core.service.token.jwt.JwtTokenService;
import com.professor_compilation.web.security.JwtAuthInterceptor;
import com.professor_compilation.web.security.UserCredentialsResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * class WebConfiguration
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final JwtTokenService jwtTokenService;

    /**
     * public class of  WebConfiguration
     * @param jwtTokenService - service of jwt token
     */
    public WebConfiguration(
            final JwtTokenService jwtTokenService
    ) {
        this.jwtTokenService = jwtTokenService;
    }


    /**
     * Add argument in resolver
     * @param argumentResolvers - argumentResolvers
     */
    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserCredentialsResolver());
    }

    /**
     * Add Interceptors
     * @param registry - registry
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(
                new JwtAuthInterceptor(jwtTokenService)
        );
    }

}
