@startuml
actor Actor as User
/'
actor       Actor       as Foo1
boundary    Boundary    as Foo2
control     Control     as Foo3
entity      Entity      as Foo4
database    Database    as Foo5
collections Collections as Foo6
queue       Queue       as Foo7

Foo -> Foo1 : To actor 
Foo -> Foo2 : To boundary
Foo -> Foo3 : To control
Foo -> Foo4 : To entity
Foo -> Foo5 : To database
Foo -> Foo6 : To collections
Foo -> Foo7 : To queue

participant ExecutionStrategy as es
'/

participant CompileController as cc
participant CompilerServiceDefault as csd
participant ExecutionFactory as ef
participant DockerContainerService as dcs

== Compile Code ==

User -> cc: POST: executeTask(taskId: String, sourceCode: MultipartFile, language: Language) \n Validate solution of user

cc -> csd: executeTask(taskId: String, sourceCode: MultipartFile, language: Language)

csd -> ef: Create Execute model
ef --> csd: Execute model

csd -> dcs: buildImage(execute)

ef --> cc: return Execute model
cc --> User: ExecuteResponse()
@enduml
