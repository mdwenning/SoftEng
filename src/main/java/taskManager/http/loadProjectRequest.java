package taskManager.http;

public class loadProjectRequest {
    public String name;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public loadProjectRequest(String name){
        this.name = name;
    }
    public loadProjectRequest(){}
}
