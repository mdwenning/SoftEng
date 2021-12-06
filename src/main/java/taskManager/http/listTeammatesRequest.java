package taskManager.http;

public class listTeammatesRequest {
    public String name;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public listTeammatesRequest(String name){
        this.name = name;
    }
    public listTeammatesRequest(){}
}
