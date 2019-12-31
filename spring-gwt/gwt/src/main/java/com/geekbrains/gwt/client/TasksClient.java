package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/tasks")
public interface TasksClient extends RestService {
    @GET
    @Path("/")
    void getAllTasks(@HeaderParam("Authorization") String token, @QueryParam("nameFilter") String nameFilter
            , @QueryParam("ownerFilter") String ownerFilter, @QueryParam("executerFilter") String executerFilter
            , @QueryParam("statusFilter") String statusFilter, MethodCallback<List<TaskDto>> tasks);

    @POST
    @Path("/")
    void addTask(TaskDto taskDto, @HeaderParam("Authorization") String token, MethodCallback<TaskDto> result);

    @GET
    @Path("remove/{id}")
    void removeTask(@HeaderParam("Authorization") String token, @PathParam("id") String id, MethodCallback<Void> result);
}
