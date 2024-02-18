@startuml
entity "verificationStatus" as verificationStatus  {
*verification_status_id UUID  PRIMARY KEY,
*task_id UUID,
*student_id UUID,
*teacher_id UUID,
*status ENUM_STATUS,
*created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
*updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
}
@enduml