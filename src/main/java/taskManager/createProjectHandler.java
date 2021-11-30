package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import taskManager.http.createProjectRequest;
import taskManager.http.createProjectResponse;

public class createProjectHandler implements RequestHandler<createProjectRequest, createProjectResponse> {

    @Override
    public createProjectResponse handleRequest(createProjectRequest req, Context context) {
        createProjectResponse response;
        response = new createProjectResponse();
        return response;
    }
}
