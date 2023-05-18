CREATE TABLE IF NOT EXISTS study_group (
    study_group_id VARCHAR(36)  PRIMARY KEY,
    study_group_name VARCHAR(36),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

DROP TRIGGER IF EXISTS update_study_group__update_time ON study_group;
CREATE TRIGGER update_study_group__update_time BEFORE UPDATE ON study_group
    FOR EACH ROW EXECUTE PROCEDURE update_time();