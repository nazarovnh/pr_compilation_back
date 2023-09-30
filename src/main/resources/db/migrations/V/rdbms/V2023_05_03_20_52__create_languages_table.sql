CREATE TABLE IF NOT EXISTS languages (
    language_id VARCHAR(36) PRIMARY KEY,
    language_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

DROP TRIGGER IF EXISTS update_languages__update_at ON languages;
CREATE TRIGGER update_languages__update_at BEFORE UPDATE ON languages
    FOR EACH ROW EXECUTE PROCEDURE update_time();