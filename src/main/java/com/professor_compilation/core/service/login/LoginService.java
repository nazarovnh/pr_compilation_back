package com.professor_compilation.core.service.login;

import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.core.model.exception.login.LoginFailedException;
import com.professor_compilation.core.repository.user.IUserRepository;
import com.professor_compilation.core.security.PasswordEncoder;
import com.professor_compilation.core.service.validator.IValidatorService;
import com.professor_compilation.web.model.user.SignInRequest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * class LoginService
 */
public class LoginService implements ILoginService {

    private final IUserRepository<User, String> users;
    private final PasswordEncoder passwordEncoder;
    private final IValidatorService validatorService;

    /**
     * class constructor of LoginService
     *
     * @param users           - repository of user
     * @param passwordEncoder - encoder of password
     */
    public LoginService(final IUserRepository users, final PasswordEncoder passwordEncoder, IValidatorService validatorService) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.validatorService = validatorService;
    }

    /**
     * Login user in system
     *
     * @param login - login request
     * @return info about user
     */
    @Override
    public User login(final SignInRequest login) throws LoginFailedException {
        Optional<User> userOptional = users.getUserInfoByEmail(login.getEmail());
        User user = validatorService.validateExistenceEntity(userOptional, "USER " + login.getEmail() + " NOT FOUND", LoginFailedException.class);

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new LoginFailedException("WRONG PASSWORD FROM USER " + login.getEmail(), HttpStatus.NOT_FOUND);
        }

        return new User(user.getUserId(), user.getUsername(), user.getRoles());
    }
}




