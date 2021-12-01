package taskManager.model;

import java.util.UUID;

public class Teammate {
    public final String name;
    public final String idTeammate;
    public final String idProject;

    public Teammate(String name, String idProject){
        this.name = name;
        this.idProject = idProject;
        this.idTeammate = UUID.randomUUID().toString();
    }
    public Teammate(String name, String idTeammate, String idProject){
        this.name = name;
        this.idTeammate = idTeammate;
        this.idProject = idProject;
    }
}
