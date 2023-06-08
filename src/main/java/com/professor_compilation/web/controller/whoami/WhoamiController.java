package com.professor_compilation.web.controller.whoami;



import com.professor_compilation.core.annotations.AuthRoleRequired;
import com.professor_compilation.web.model.security.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to display the current user.
 */
@Controller
@RequestMapping("/whoiam")
public class WhoamiController {

    /**
     * Get info about user using jwt token
     * @param userCredentials -credentials of user
     * @return info about user
     */
    @GetMapping
    @ResponseBody
    @AuthRoleRequired("USER")
    public ResponseEntity<UserCredentials> get(
            final UserCredentials userCredentials
    ) {
        return ResponseEntity.ok(userCredentials);
    }

}

