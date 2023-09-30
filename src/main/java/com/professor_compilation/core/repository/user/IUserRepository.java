package com.professor_compilation.core.repository.user;

import com.professor_compilation.core.model.exception.signup.SignUpException;

import java.util.List;
import java.util.Optional;

public interface IUserRepository <T, ID> {
    /**
     * Get all id of users
     * @return list of id of users
     */
    List<ID> getAllUsersId();

    /**
     * Get name of user by id
     *
     * @param userId - id of user
     * @return name of user
     */
    Optional<ID> getUserNameById(final ID userId) ;

    /**
     * Get info about user by email
     * @param userId - id of user
     * @return info about user
     */
    Optional<T> getUserInfoByEmail(final ID userId);

    /**
     * Get info about user by email
     * @param userId - id of user
     * @return info about user
     */
    Optional<T> getUserInfoById(final ID userId);

    /**
     * Add user in repository
     * @param email - email of user
     * @param password - password of user
     * @throws SignUpException - SignUpFailedException
     */
    ID addUser(final String email, final String password) throws SignUpException;

}
