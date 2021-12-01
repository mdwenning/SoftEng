package taskManager.model;

public class Assignment {
    public final String idTeammate;
    public final String idTask;

    public Assignment(String idTask, String idTeammate){
        this.idTask = idTask;
        this.idTeammate = idTeammate;
    }
}
