CREATE TABLE IF NOT EXISTS user_roles (
    user_id VARCHAR(36),
    role VARCHAR(16) CHECK (role IN ('ANONYMOUS', 'USER', 'TEACHER', 'ADMIN')),
    PRIMARY KEY (user_id, role),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_user_roles__user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
                                           ON UPDATE CASCADE
);

DROP TRIGGER IF EXISTS update_user_roles__update_time ON user_roles;
CREATE TRIGGER update_user_roles__update_time BEFORE UPDATE ON user_roles
    FOR EACH ROW EXECUTE PROCEDURE update_time();