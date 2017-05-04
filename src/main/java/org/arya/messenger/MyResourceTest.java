package org.arya.messenger;

import org.arya.messenger.model.Message;
import org.arya.messenger.resources.MessageResource;
import org.arya.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by arya's on 5/1/2017.
 */

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyResourceTest {
    MessageService messageService= new MessageService();

    @GET
    public List<Message> getMessage(){
        return messageService.getAllMessages();
    }

}