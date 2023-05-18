CREATE TABLE IF NOT EXISTS subject (
    subject_id VARCHAR(36) PRIMARY KEY,
    number_hours INT,
    subject_title VARCHAR(128) NOT NULL,
    subject_description TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT unique_subject_hours UNIQUE (subject_id, number_hours)
);

DROP TRIGGER IF EXISTS update_subject__update_at ON subject;
CREATE TRIGGER update_subject__update_at BEFORE UPDATE ON subject
    FOR EACH ROW EXECUTE PROCEDURE update_time();

