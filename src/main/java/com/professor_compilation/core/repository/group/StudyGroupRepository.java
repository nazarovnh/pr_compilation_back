package com.professor_compilation.core.repository.group;

import com.professor_compilation.core.entity.group.StudyGroupRdbms;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;

import static com.professor_compilation.utils.rowmapper.EntityRowMapper.getEntityRowMapper;

public class StudyGroupRepository implements IStudyGroupRepository<StudyGroupRdbms, String>{
    private final NamedParameterJdbcOperations jdbcOperations;
    private final String stampStudyGroupId = "studyGroupId";

    public StudyGroupRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<StudyGroupRdbms> findAllByStudyGroupId(String studyGroupId) {
        String sql = String.format("SELECT * FROM study_group_user WHERE study_group_id = :%s", stampStudyGroupId);
        return jdbcOperations.query(sql, new MapSqlParameterSource(stampStudyGroupId, studyGroupId), getEntityRowMapper(StudyGroupRdbms.class));
    }
}
