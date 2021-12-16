package taskManager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {
    public final String idTask;
    public final String idProject;
    public final String idParent;
    public final String name;
    public List<String> assignees;
    public final int isComplete;
    public final int sequence;

    public Task(String idTask, String idProject, String idParent, String name, int isComplete, int sequence){
        this.idTask = idTask;
        this.idProject = idProject;
        this.idParent = idParent;
        this.name = name;
        this.isComplete = isComplete;
        this.sequence = sequence;
        this.assignees = new ArrayList<>();
    }
    public Task(String idTask, String idProject, String idParent, String name, List<String> assignees, int isComplete, int sequence){
        this.idTask = idTask;
        this.idProject = idProject;
        this.idParent = idParent;
        this.name = name;
        this.isComplete = isComplete;
        this.sequence = sequence;
        this.assignees = assignees;
    }
    public Task(String name, String idProject, int sequence){
        this.name = name;
        this.idProject = idProject;
        this.idParent = null;
        this.assignees = new ArrayList<>();
        this.idTask = UUID.randomUUID().toString();
        this.isComplete = 0;
        this.sequence = sequence;
    }
    public Task(String name, String idProject, String idParent, int sequence){
        this.name = name;
        this.idProject = idProject;
        this.idParent = idParent;
        this.assignees = new ArrayList<>();
        this.idTask = UUID.randomUUID().toString();
        this.isComplete = 0;
        this.sequence = sequence;
    }
}
