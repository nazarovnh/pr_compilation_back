@startuml
entity "studyGroup" as studyGroup {
*study_group_id VARCHAR(36)  PRIMARY KEY,
--
*study_group_name VARCHAR(36),
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
}


entity "Subject" as subject {
*subject_id VARCHAR(36) PRIMARY KEY,
*number_hours INT,
--
*subject_title VARCHAR(128) NOT NULL,
*subject_description TEXT,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()

}

entity "studyGroupSubject" as studyGroupSubject {
*subject_id VARCHAR(36) REFERENCES subject(subject_id) ON UPDATE CASCADE,
*study_group_id VARCHAR(36) REFERENCES study_group(study_group_id) ON UPDATE CASCADE,
--
}

entity "Topic" as topic {
*topic_id VARCHAR(36) PRIMARY KEY,
--
*subject_id VARCHAR(36),
*topic_title VARCHAR(128) NOT NULL,
*topic_description TEXT,
*topic_order INT NOT NULL,
*threshold_score INT NOT NULL,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}


entity "Task" as task {
*task_id VARCHAR(36) PRIMARY KEY,
--
*topic_id VARCHAR(36) NOT NULL,
*task_title VARCHAR(256) NOT NULL,
*task_description VARCHAR(1024) NOT NULL,
*task_order INT NOT NULL,
*time_limit INT NOT NULL,
*memory_limit INT NOT NULL,
*task_price INT NOT NULL,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}

entity "TestCase" as testCase {
*test_сase_id : VARCHAR(36) PRIMARY KEY
--
*task_id VARCHAR(36),
*input VARCHAR(128) NOT NULL,
*correct_output VARCHAR(128) NOT NULL,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}

entity "SolutionAttempt" as userSolution {
*attempt_id : VARCHAR(36) PRIMARY KEY,
--
*user_id : VARCHAR(36),
*task_id: VARCHAR(36),
*filename VARCHAR(64) NOT NULL,
*file_data BYTEA NOT NULL,
*attempt_score INT NOT NULL,
*execution_time INT NOT NULL,
*programming_language VARCHAR(255) NOT NULL,
*attempt_status VARCHAR(32) CHECK (attempt_status IN ('SUCCESS', 'FAILED', 'TIMEOUT', 'MEMORY_OUT'))
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}

entity "studyGroupStundent" as studyGroupStudent {
*study_group_id : VARCHAR(36) PRIMARY KEY,
*student_id : VARCHAR(36) PRIMARY KEY
--
}

entity "User" as user {
*user_id : VARCHAR(36) PRIMARY KEY
--
*email VARCHAR(320) NOT NULL,
*password VARCHAR(256) NOT NULL,
*name : VARCHAR(32) NOT NULL,
*surname : VARCHAR(32) NOT NULL,
*patronymic: VARCHAR(32) NOT NULL,
*password: VARCHAR(256) NOT NULL,
*enabled : BOOLEAN NOT NULL DEFAULT false,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}


entity "UserRole" as userRole {
*user_id : VARCHAR(36) PRIMARY KEY
--
*role : VARCHAR(16) CHECK (role IN ('ANONYMOUS', 'USER', 'TEACHER', 'ADMIN')),
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}

entity "Language" as language {
*language_id VARCHAR(36) PRIMARY KEY,
--
*language_name VARCHAR(255) NOT NULL,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
}


entity "TaskLanguage" as taskLanguage {
*task_id VARCHAR(36) REFERENCES task(task_id) ON UPDATE CASCADE,
*language_id VARCHAR(36) REFERENCES languages(language_id) ON UPDATE CASCADE,
}


entity "TopicAccess" as userAccessSubject {
*user_id VARCHAR(36) NOT NULL REFERENCES users(user_id),
*topic_id VARCHAR(36) NOT NULL REFERENCES topics(topic_id),
--
*max_sum_score INT NOT NULL,
*access_by_score BOOLEAN NOT NULL,
*access_from_teacher BOOLEAN NOT NULL DEFAULT false,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}



task }|-- testCase

studyGroup }|-- studyGroupStudent
user }|-- studyGroupStudent

task }|-- userSolution
user }|-- userSolution

topic }|-- userAccessSubject
user }|-- userAccessSubject

studyGroup }|-- studyGroupSubject
subject }|-- studyGroupSubject

subject }|-- topic

user }|-- userRole

topic }|-- task

task }|-- taskLanguage
language }|-- taskLanguage
@enduml