package com.professor_compilation.web.controller.subject;

import com.professor_compilation.core.annotations.AuthRoleRequired;
import com.professor_compilation.core.entity.subject.rdbms.Subject;
import com.professor_compilation.core.service.subject.ISubjectService;
import com.professor_compilation.web.model.security.UserCredentials;
import com.professor_compilation.web.model.subject.request.SubjectCreateRequest;
import com.professor_compilation.web.model.subject.request.SubjectJoinRequest;
import com.professor_compilation.web.model.subject.request.SubjectPatchRequest;
import com.professor_compilation.web.model.subject.response.SubjectCreateResponse;
import com.professor_compilation.web.model.subject.response.SubjectGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {
    private final ISubjectService subjectService;
    @PostMapping("/")
    public ResponseEntity<SubjectCreateResponse> createSubject(final @RequestBody SubjectCreateRequest subject) {
        return new ResponseEntity(subjectService.createSubject(subject), HttpStatus.CREATED);
    }

    /**
     * Method for join study group to subject
     * @param subjectId - id of subject
     * @param subject - subject request
     * @return http status
     */
    @PostMapping("/{subjectId}/join")
    @AuthRoleRequired("TEACHER")
    public ResponseEntity<HttpStatus> joinSubject(final @PathVariable String subjectId, final @RequestBody SubjectJoinRequest subject) {
        subjectService.joinSubject(subjectId, subject);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(final @PathVariable String id) {
        return new ResponseEntity(subjectService.getSubjectById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/topics")
    public ResponseEntity<SubjectGetResponse> getSubjectOverviewById(final @PathVariable String id, final UserCredentials userCredentials) {
        return new ResponseEntity(subjectService.getSubjectTopicsById(id, userCredentials.getUserId()), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return new ResponseEntity(subjectService.getAllSubjects(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Subject> patchSubjectById(final @PathVariable String id, final @RequestBody SubjectPatchRequest subjectPatchRequest) {
        return new ResponseEntity(subjectService.patchSubjectById(id, subjectPatchRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable String id) {
        subjectService.deleteSubjectById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
