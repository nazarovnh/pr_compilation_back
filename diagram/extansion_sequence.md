@startuml
Browser_Extension -> EvaluationController: createEvaluationStatus(studentEmail: String, taskId: String) get email of teacher from cookies
EvaluationController -> EvaluationService: createVerificationStatus(studentEmail: String, taskId: String)
EvaluationService -> EvaluationRepository: getEvaluation(studentEmail: String, taskId: String, teacherId)
EvaluationRepository --> EvaluationService: Evaluation evaluation
alt evaluation == null
EvaluationService -> EvaluationRepository: createEvaluation(studentEmail: String, taskId: String, teacherId)
else status higer than IN_PROGRESS -> do nothing
end   
EvaluationService --> EvaluationController:
@enduml