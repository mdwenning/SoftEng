package taskManager.model;
import java.util.UUID;

public class Project {
    public final String name;
    public final String idProject;
    public final int isArchived;
    public final String percentComplete;

    public Project(String name){
        this.name = name;
        this.idProject = UUID.randomUUID().toString();
        this.isArchived = 0;
        this.percentComplete = "";
    }
    public Project(String name, String idProject, int isArchived, String percentComplete){
        this.name = name;
        this.idProject = idProject;
        this.isArchived = isArchived;
        this.percentComplete = percentComplete;
    }
}
