CREATE TABLE IF NOT EXISTS token (
    token_id VARCHAR(36),
    user_id VARCHAR(36),
    PRIMARY KEY (token_id, user_id),
    refresh_token TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_user_roles__user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
                                           ON UPDATE CASCADE
);

DROP TRIGGER IF EXISTS update_token__update_at ON token;
CREATE TRIGGER update_token__update_at BEFORE UPDATE ON token
    FOR EACH ROW EXECUTE PROCEDURE update_time();
