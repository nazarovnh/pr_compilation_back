@startuml
actor Actor as User

participant ExecutionStrategy as es

participant CompileController as cc
participant CompilerServiceDefault as csd
participant TaskService as ts
participant ExecutionSevice as es
participant ExecutionFactory as ef
participant StrategyService as ss
participant DockerContainerService as dcs
participant CmdUtils as cmd
participant ProcessBuilder as pb

== Compile Code ==

User -> cc: POST: executeTask(taskId: String, sourceCode: MultipartFile, language: Language) \n Validate solution of user

cc -> csd: executeTask(taskId: String, sourceCode: MultipartFile, language: Language)
csd -> ts: getInfoAboutTask(taskId: String)\n get model TaskInfo
ts --> csd: model TaskInfo

csd -> es: createExecution(taskId: String, sourceCode: MultipartFile, language: Language)\n Create Execute model

es -> ef: createExecution(MultipartFile sourceCode, TaskInfo taskInfo)
ef --> es: Execution
es --> csd: Execution \\ fill TaskInfo
csd -> es: executeCode(Execution: execution)

es -> es: buildExecutionEnviroment(Execution: execution) \n Excecution invoke createExecutionDirectory()
es -> ss: getExecutionStrategy(Language language)
ss --> es: CompilatorStrategyService or InterpetatorStrategyService
ss -> ss: maybe check if code can compile if we can not handle this in runContainer
es -> ss: executeStrategy(Execution: execution) // method in abstract class ExecutionStrategy
ss -> dcs: buildImage(Execution: execution)
loop execution.getTestCases() times
ss -> ss: executeTestCase(execution, testCase)
ss -> dcs: runContainer(String imageName, String containerName, long timeout, String volumeMounting, String executionPath, String sourceCodeFileName)
dcs -> cmd: runContainer(dockerCommand, timeout)
cmd -> pb: ProcessBuilder(commands).start()
pb --> cmd: ExecutionResult
cmd --> dcs: ExecutionResult
dcs --> ss: ExecutionResult
ss --> ss: validateAnswerExecutionResult
end
ss --> es: CodeResponse
es  --> csd: CodeResponse
csd --> cc: CodeResponse
cc --> User: CodeResponse
@enduml