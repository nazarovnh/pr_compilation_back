package com.professor_compilation.config.utils;

import com.professor_compilation.utils.file.FileUtils;
import com.professor_compilation.utils.process.ProcessUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public FileUtils fileUtils(){
        return new FileUtils();
    }

    @Bean
    public ProcessUtils processUtils(){
        return new ProcessUtils();
    }
}
