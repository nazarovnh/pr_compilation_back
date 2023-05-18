CREATE TABLE IF NOT EXISTS test_case (
    test_case_id VARCHAR(36) PRIMARY KEY,
    task_id VARCHAR(36),
    input VARCHAR(128) NOT NULL,
    correct_output VARCHAR(128) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_test_case__task_id FOREIGN KEY(task_id) REFERENCES task(task_id)
    ON UPDATE CASCADE
);

DROP TRIGGER IF EXISTS update_test_case__update_at ON test_case;
CREATE TRIGGER update_test_case__update_at BEFORE UPDATE ON test_case
    FOR EACH ROW EXECUTE PROCEDURE update_time();