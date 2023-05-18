package com.professor_compilation.core.entity.task;

public interface ITask {

    public String getTaskId();
    public void setTaskId(String taskId);
    public String getTaskTitle() ;

    public void setTaskTitle(String taskTitle);

    public String getTaskDescription();

    public void setTaskDescription(String taskDescription);

    public int getTimeLimit() ;

    public void setTimeLimit(int timeLimit);
    public int getMemoryLimit() ;
    public void setMemoryLimit(int memoryLimit) ;
}
