package taskManager.model;

public class Assignment {
    public final String idTeammate;
    public final String idTask;
    public final String idProject;

    public Assignment(String idTask, String idTeammate, String idProject){
        this.idTask = idTask;
        this.idTeammate = idTeammate;
        this.idProject = idProject;
    }
}
