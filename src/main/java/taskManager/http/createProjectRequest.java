package taskManager.http;

public class createProjectRequest {
    String name;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public createProjectRequest(String name){
        this.name = name;
    }
    public createProjectRequest(){}
}
