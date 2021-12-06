package taskManager.http;

public class deleteProjectRequest {
    public String name;
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public deleteProjectRequest(String name){this.name = name;}
    public deleteProjectRequest(){}
    public String toString(){return "Delete("+name+")";}
}
