CREATE TABLE IF NOT EXISTS study_group_user(
    user_id VARCHAR(36) REFERENCES users(user_id) ON UPDATE CASCADE,
    study_group_id VARCHAR(36) REFERENCES study_group(study_group_id) ON UPDATE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

DROP TRIGGER IF EXISTS update_study_group_user__update_time ON study_group_user;
CREATE TRIGGER update_study_group_user__update_time BEFORE UPDATE ON study_group_user
    FOR EACH ROW EXECUTE PROCEDURE update_time();