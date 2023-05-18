CREATE TABLE IF NOT EXISTS topic_access (
    user_id VARCHAR(36),
    topic_id VARCHAR(36),
    PRIMARY KEY (user_id, topic_id),
    max_sum_score INT NOT NULL,
    access_by_score BOOLEAN NOT NULL,
    access_from_teacher BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_topic_access__user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
                         ON UPDATE CASCADE,
    CONSTRAINT fk_topic_access__topic_id FOREIGN KEY(topic_id) REFERENCES topic(topic_id)
                         ON UPDATE CASCADE
);

DROP TRIGGER IF EXISTS update_topic_access__update_time ON topic_access;
CREATE TRIGGER update_topic_access__update_time BEFORE UPDATE ON topic_access
    FOR EACH ROW EXECUTE PROCEDURE update_time();