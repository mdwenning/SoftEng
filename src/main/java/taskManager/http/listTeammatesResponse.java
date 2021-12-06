package taskManager.http;

import taskManager.model.Teammate;

import java.util.ArrayList;
import java.util.List;

public class listTeammatesResponse {
    public final List<Teammate> list;
    public final int statusCode;
    public final String error;

    public listTeammatesResponse(List<Teammate> list, int code){
        this.list = list;
        this.statusCode = code;
        this.error = "";
    }
    public listTeammatesResponse(int code, String error){
        this.list = new ArrayList<>();
        this.statusCode = code;
        this.error = error;
    }
}
