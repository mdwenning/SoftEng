package taskManager.http;

public class listTasksRequest {
    public String name;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public listTasksRequest(String name){
        this.name = name;
    }
    public listTasksRequest(){}
}
