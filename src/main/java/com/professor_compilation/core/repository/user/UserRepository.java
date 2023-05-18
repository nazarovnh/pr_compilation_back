package com.professor_compilation.core.repository.user;

import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.core.model.exception.user.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.*;

@Slf4j
public class UserRepository implements IUserRepository<User, String> {
    private final JdbcOperations jdbcOperations;
    private final String stampUserId = "user_id";
    private final String stampRole = "role";
    private final String stampEmail = "email";
    private final String stampPassword = "password";


    /**
     * public construction PostgresUserRepository
     *
     * @param jdbcOperations - tool for working with db
     */
    public UserRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    /**
     * Get all id of users
     *
     * @return list of id of users
     */
    @Override
    public List<String> getAllUsersId() {
        log.info("get all users id");
        return jdbcOperations.query("SELECT user_id FROM users",
                ((resultSet, i) ->
                        resultSet.getString("user_id")
                ));
    }

    /**
     * Get name of user by id
     *
     * @param userId - id of user
     * @return name of user
     */
    @Override
    public Optional<String> getUserNameById(final String userId) {
        try {
            log.info("get email by userId {}", userId);
            String sql = "SELECT email FROM users WHERE user_id = ?";
            List<String> result = jdbcOperations.query(sql, new Object[]{userId}, (rs, rowNum) -> rs.getString("email"));
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } catch (DataAccessException e) {
            log.error("Error getting user by userId " + userId, e);
            throw new UserException("Error getting user by userId " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Get info about user by email
     *
     * @param email - email of user
     * @return info about user
     */
    @Override
    public Optional<User> getUserInfoByEmail(final String email) {
        log.info("SEARCHING USER BY EMAIL - {}", email);
        Map<String, Object> rawUser;
        try {
            rawUser = jdbcOperations.queryForMap(

                    "SELECT user_id, email, password FROM users u" +
                            " WHERE u.enabled = true AND u.email = ?",
                    email
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }


        List<String> roles = new ArrayList<>();
        jdbcOperations.query(
                "SELECT role FROM user_roles" +
                        " WHERE user_id = ?",
                resultSet -> {
                    String role = resultSet.getString(stampRole);
                    roles.add(role);
                },
                rawUser.get(stampUserId)
        );

        String userId = String.valueOf(rawUser.get(stampUserId));
        String password = String.valueOf(rawUser.get(stampPassword));
        String username = String.valueOf(rawUser.get(stampEmail));
        User user = new User(userId, username, password, roles);
        return Optional.of(user);
    }

    @Override
    public Optional<User> getUserInfoById(String userId) {
        log.info("find user by userId - {}", userId);
        Map<String, Object> rawUser;
        try {
            rawUser = jdbcOperations.queryForMap(
                    "SELECT user_id, email, password FROM users u" +
                            " WHERE u.enabled = true AND u.userId = ?",
                    userId
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }


        List<String> roles = new ArrayList<>();
        jdbcOperations.query(
                "SELECT role FROM user_roles" +
                        " WHERE user_id = ?",
                resultSet -> {
                    String role = resultSet.getString(stampRole);
                    roles.add(role);
                },
                rawUser.get(stampUserId)
        );

        String password = String.valueOf(rawUser.get(stampPassword));
        String username = String.valueOf(rawUser.get(stampEmail));
        User user = new User(userId, username, password, roles);
        return Optional.of(user);

    }

    /**
     * Add user in repository
     *
     * @param email    - email of user
     * @param password - password of user
     */
    @Override
    public String addUser(final String email, final String password) {
        log.info("add user with email - " + email);
        final String userId = UUID.randomUUID().toString();
        jdbcOperations.update("INSERT INTO users (user_id, email, password, enabled) " +
                "VALUES(?, ?, ?, ?)", userId, email, password, true);
        jdbcOperations.update("INSERT INTO user_roles (user_id, role) " +
                "VALUES (?, ?)", userId, "USER");
        return userId;
    }

}
