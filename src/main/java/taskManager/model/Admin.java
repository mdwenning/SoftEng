package taskManager.model;
import java.util.UUID;

public class Admin {
    public final String name;
    public final String idAdmin;

    public Admin(String name){
        this.name = name;
        this.idAdmin = UUID.randomUUID().toString();
    }
    public Admin(String name, String idAdmin){
        this.name = name;
        this.idAdmin = idAdmin;
    }
}
