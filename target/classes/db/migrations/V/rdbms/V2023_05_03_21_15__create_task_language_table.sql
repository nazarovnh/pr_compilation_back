CREATE TABLE IF NOT EXISTS task_language (
    task_id VARCHAR(36) REFERENCES task(task_id) ON UPDATE CASCADE,
    language_id VARCHAR(36) REFERENCES languages(language_id) ON UPDATE CASCADE,
    PRIMARY KEY (task_id, language_id)
);
