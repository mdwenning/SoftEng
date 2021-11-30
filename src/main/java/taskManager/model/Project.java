package taskManager.model;

import java.util.UUID;

public class Project {
    public final String name;
    public final UUID idProject;

    public Project(String name){
        this.name = name;
        this.idProject = UUID.randomUUID();
    }
}
