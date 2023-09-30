package com.professor_compilation.core.repository.subject;

import com.professor_compilation.core.entity.subject.rdbms.Subject;
import com.professor_compilation.core.model.exception.NoSuchElementFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static com.professor_compilation.constants.Constants.SUBJECT;
import static com.professor_compilation.constants.Constants.getMessageNoSuchEntity;
import static com.professor_compilation.utils.parameter.ParameterUtils.getParameters;
import static com.professor_compilation.utils.rowmapper.EntityRowMapper.getEntityRowMapper;

public class SubjectRepository implements ISubjectRepository<Subject, String> {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final String stampSubjectId = "subjectId";
    private final String stampNumberHours = "numberHours";
    private final String stampSubjectTitle = "subjectTitle";
    private final String stampSubjectDescription = "subjectDescription";
    private final String stampStudyGroupId = "studyGroupId";

    public SubjectRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Optional<Subject> findById(final String subjectId) {
        try {
            String sql = String.format("SELECT * FROM subject WHERE subject_id = :%s", stampSubjectId);
            Subject subject = jdbcOperations.queryForObject(sql,
                    new MapSqlParameterSource(stampSubjectId, subjectId), getEntityRowMapper(Subject.class));
            return Optional.of(subject);
        } catch (
                EmptyResultDataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(SUBJECT, subjectId));
        }
    }

    @Override
    public Collection<Subject> findAll() {
        String sql = "SELECT * FROM subject";
        return jdbcOperations.query(sql, getEntityRowMapper(Subject.class));
    }

    @Override
    public Subject save(final Subject subject) {
        String subjectId = UUID.randomUUID().toString();
        subject.setSubjectId(subjectId);
        String sql = "INSERT INTO subject (subject_id, number_hours, subject_title, subject_description) "
                + "VALUES (:%s, :%s, :%s, :%s)";
        sql = String.format(sql, stampSubjectId, stampNumberHours, stampSubjectTitle, stampSubjectDescription);
        MapSqlParameterSource params = getParameters(subject);
        jdbcOperations.update(sql, params);
        return subject;
    }

    @Override
    public void deleteById(final String subjectId) {
        String sql = String.format("DELETE FROM subject WHERE subject_id = :%s", stampSubjectId);
        try {
            jdbcOperations.update(sql, new MapSqlParameterSource(stampSubjectId, subjectId));
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(getMessageNoSuchEntity(SUBJECT, subjectId));
        }
    }

    @Override
    public Subject update(final Subject subject) {
        String sql = String.format("UPDATE subject SET number_hours = :%s, subject_title = :%s, subject_description = :%s WHERE subject_id = :%s",
                stampNumberHours, stampSubjectTitle, stampSubjectDescription, stampSubjectId);
        MapSqlParameterSource params = getParameters(subject);
        try {
            jdbcOperations.update(sql, params);
        } catch (DataAccessException e) {
            throw new NoSuchElementFoundException(String.format(getMessageNoSuchEntity(SUBJECT, subject.getSubjectId())));
        }
        return subject;
    }

    @Override
    public void saveStudyGroup(final String subjectId, final String studyGroupId) {
        String sql = String.format("INSERT INTO study_group_subject (subject_id, study_group_id) "
                + "VALUES (:%s, :%s)", stampSubjectId, stampStudyGroupId);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(stampSubjectId, subjectId);
        params.addValue(stampStudyGroupId, studyGroupId);
        jdbcOperations.update(sql, params);
    }

}
