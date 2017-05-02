package org.arya.messenger;

import org.arya.messenger.model.Message;
import org.arya.messenger.resources.MessageResource;
import org.arya.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by arya's on 5/1/2017.
 */

@Path("/users")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class MyResourceTest {
    MessageService messageService= new MessageService();

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
        Message msg= messageService.getMessage(id);
        String str=uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(msg.getId()))
                .build()
                .toString();
        msg.addLink(str,"self");
        return msg;
    }

}