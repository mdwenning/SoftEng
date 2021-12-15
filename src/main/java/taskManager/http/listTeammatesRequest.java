package taskManager.http;

public class listTeammatesRequest {
    public String projName;

    public String getName(){return projName;}
    public void setName(String projectName){this.projName = projectName;}
    public listTeammatesRequest(String projectName){
        this.projName = projectName;
    }
    public listTeammatesRequest(){}
}
