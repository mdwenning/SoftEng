package taskManager.model;

import java.util.UUID;

public class Task {
    public final String idTask;
    public final String idProject;
    public final String idParent;
    public final String name;
    public final int isComplete;
    public final int sequence;

    public Task(String idTask, String idProject, String idParent, String name, int isComplete, int sequence){
        this.idTask = idTask;
        this.idProject = idProject;
        this.idParent = idParent;
        this.name = name;
        this.isComplete = isComplete;
        this.sequence = sequence;
    }
    public Task(String name, String idProject, int sequence){
        this.name = name;
        this.idProject = idProject;
        this.idParent = null;
        this.idTask = UUID.randomUUID().toString();
        this.isComplete = 0;
        this.sequence = sequence;
    }
}
