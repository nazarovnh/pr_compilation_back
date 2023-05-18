package com.professor_compilation.config.constants;

import com.professor_compilation.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstantsConfig {
    @Bean
    public Constants constants() {
        return new Constants();
    }
}
