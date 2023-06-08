package com.professor_compilation.web.controller.compile;

import com.professor_compilation.core.annotations.AuthRoleRequired;
import com.professor_compilation.core.model.exception.environment.EnvironmentException;
import com.professor_compilation.core.model.exception.process.ProcessException;
import com.professor_compilation.core.model.language.Language;
import com.professor_compilation.core.service.compile.ICompileService;
import com.professor_compilation.web.model.compile.CodeRequest;
import com.professor_compilation.web.model.compile.CodeResponse;
import com.professor_compilation.web.model.security.UserCredentials;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * CompileController
 * Controller works with request from client side related with execute of tasks
 */
@RestController
@AllArgsConstructor
@RequestMapping("/compile")
public class CompileController {

    private ICompileService compileService;


    @PostMapping(value = "topic/{topicId}/task/{taskId}/execute/{language}"
    )
    @AuthRoleRequired("USER")
    public ResponseEntity<CodeResponse> executeTask(@PathVariable final String topicId,
                                                    @PathVariable final String taskId,
                                                    @RequestPart("sourceCode") MultipartFile sourceCode,
                     @PathVariable final String language,
                                                    final UserCredentials userCredentials
    ) throws ProcessException, EnvironmentException {
        return ResponseEntity.ok(compileService.execute(topicId, taskId, new CodeRequest(Language.valueOf(language)),
                sourceCode, userCredentials));
    }
}
