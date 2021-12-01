package taskManager.model;

public class Task {
    public final String idTask;
    public final String idProject;
    public final String idParent;
    public final String name;
    public final int isComplete;

    public Task(String idTask, String idProject, String idParent, String name, int isComplete){
        this.idTask = idTask;
        this.idProject = idProject;
        this.idParent = idParent;
        this.name = name;
        this.isComplete = isComplete;
    }
}
