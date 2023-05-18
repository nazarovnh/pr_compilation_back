CREATE TABLE IF NOT EXISTS task (
    task_id VARCHAR(36) PRIMARY KEY,
    topic_id VARCHAR(36) NOT NULL,
    task_title VARCHAR(256) NOT NULL,
    task_description VARCHAR(1024) NOT NULL,
    task_order INT NOT NULL,
    time_limit INT NOT NULL,
    memory_limit INT NOT NULL,
    task_price INT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_task__topic_id FOREIGN KEY(topic_id) REFERENCES topic(topic_id)
    ON UPDATE CASCADE
);

DROP TRIGGER IF EXISTS update_task__update_at ON task;
CREATE TRIGGER update_task__update_at BEFORE UPDATE ON task
    FOR EACH ROW EXECUTE PROCEDURE update_time();