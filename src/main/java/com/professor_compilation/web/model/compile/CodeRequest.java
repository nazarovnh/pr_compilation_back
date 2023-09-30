package com.professor_compilation.web.model.compile;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.professor_compilation.core.model.language.Language;
public class CodeRequest {
    private Language language;

    @JsonCreator
    public CodeRequest(@JsonProperty("language") Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
