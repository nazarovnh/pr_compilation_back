CREATE TABLE IF NOT EXISTS subject_language (
    subject_id VARCHAR(36) REFERENCES subject(subject_id) ON UPDATE CASCADE,
    language_id VARCHAR(36) REFERENCES languages(language_id) ON UPDATE CASCADE,
    PRIMARY KEY (subject_id, language_id)
);
