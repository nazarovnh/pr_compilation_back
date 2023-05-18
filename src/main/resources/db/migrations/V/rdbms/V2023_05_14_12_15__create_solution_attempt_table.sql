CREATE TABLE IF NOT EXISTS solution_attempt (
    attempt_id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    task_id VARCHAR(36),
    filename VARCHAR(64) NOT NULL,
    file_data BYTEA NOT NULL,
    attempt_score INT NOT NULL,
    execution_time INT NOT NULL,
    programming_language VARCHAR(255) NOT NULL,
    attempt_status VARCHAR(32) CHECK (attempt_status IN ('SUCCESS', 'FAILED', 'TIMEOUT', 'MEMORY_OUT')),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_solution_attempt__user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
    ON UPDATE CASCADE,
    CONSTRAINT fk_solution_attempt__task_id FOREIGN KEY(task_id) REFERENCES task(task_id)
    ON UPDATE CASCADE
);

DROP TRIGGER IF EXISTS update_solution_attempt__update_time ON solution_attempt;
CREATE TRIGGER update_solution_attempt__update_time BEFORE UPDATE ON solution_attempt
    FOR EACH ROW EXECUTE PROCEDURE update_time();