package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.ItemDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/items")
public interface ItemsClient extends RestService {
    @GET
    void getAllItems(MethodCallback<List<ItemDto>> items);

    @GET
    @Path("remove/{id}")
    void removeItem(@PathParam("id") String id, MethodCallback<Void> result);
}
