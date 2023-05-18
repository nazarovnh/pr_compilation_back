package com.professor_compilation.core.model.execution.languages;

import com.professor_compilation.core.model.execution.Execution;
import com.professor_compilation.core.model.language.Language;
import org.springframework.stereotype.Component;

@Component
public class PythonExecution extends Execution {
    private Language language = Language.python;
    private String extension = "py";
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
