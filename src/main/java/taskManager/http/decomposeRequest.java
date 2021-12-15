package taskManager.http;

public class decomposeRequest {
    public String name;
    public String projectName;
    public String idParent;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getProjectName(){return projectName;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public String getIdParent(){return idParent;}
    public void setIdParent(String idParent){this.idParent = idParent;}
    public decomposeRequest(String name, String projectName, String  idParent){
        this.name = name;
        this.projectName = projectName;
        this.idParent = idParent;
    }
    public decomposeRequest(){}
}
