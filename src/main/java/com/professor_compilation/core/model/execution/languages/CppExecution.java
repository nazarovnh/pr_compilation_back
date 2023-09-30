package com.professor_compilation.core.model.execution.languages;

import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.language.Language;
import org.springframework.stereotype.Component;

@Component
public class CppExecution extends Execution {
    private Language language = Language.cpp;
    private String extension = "cpp";
    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public Boolean isCompiled() {
        return false;
    }

    @Override
    public String getExtension() {
        return extension;
    }
}
