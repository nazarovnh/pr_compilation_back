package com.professor_compilation.config.security;

import com.professor_compilation.core.security.BCryptPasswordEncoder;
import com.professor_compilation.core.security.PasswordEncoder;
import com.professor_compilation.core.service.token.JwtSettings;
import com.professor_compilation.core.service.token.jwt.JsonWebTokenService;
import com.professor_compilation.core.service.token.jwt.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class SecurityConfig.
 * Class contains beans related with security.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean for encoder of password
     * @return encoder of password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for service of token
     * @param settings - basic setting for token
     * @return service of token
     */
    @Bean
    public JwtTokenService jwtTokenService(final JwtSettings settings) {
        return new JsonWebTokenService(settings);
    }

}
