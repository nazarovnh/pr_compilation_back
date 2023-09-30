CREATE TABLE IF NOT EXISTS topic (
    topic_id VARCHAR(36) PRIMARY KEY,
    subject_id VARCHAR(36),
    topic_title VARCHAR(128) NOT NULL,
    topic_description TEXT,
    topic_order INT NOT NULL,
    threshold_score INT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_topic__subject_id FOREIGN KEY(subject_id) REFERENCES subject(subject_id)
    ON UPDATE CASCADE
);

DROP TRIGGER IF EXISTS update_topic__update_at ON topic;
CREATE TRIGGER update_topic__update_at BEFORE UPDATE ON topic
    FOR EACH ROW EXECUTE PROCEDURE update_time();