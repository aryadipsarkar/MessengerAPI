package org.arya.messenger.resources;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.arya.messenger.model.Message;
import org.arya.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class MessageResource {
    MessageService messageService= new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year")int year,
                                     @QueryParam("start")int start,
                                     @QueryParam("size")int size){
        if (year>0)
            return messageService.getAllMessagesByYear(year);
        if(start>=0 && size>0)
            return messageService.getAllMessagePaginated(start, size);
        else
            return messageService.getAllMessages();
    }


    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException{
        String url= uriInfo.getAbsolutePath().toString();
        Message msg=messageService.addMessage(message);
        return Response.created(new URI(url +"/"+ msg.getId()))
                .entity(msg)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Response updateMessage(@PathParam("messageId") long id,Message message){
        //since we are updating the message id will remain the same;only content will change
        //the ID will always be picked up from the URL and that ID will be updated with the updated content
        message.setId(id);
        Message msg= messageService.updateMessage(message);
        return Response.status(Status.CREATED)
                .entity(msg)
                .build();
    }

    @DELETE
    @Path("/{messageId}")
    public void removeMessage(@PathParam("messageId") long id){
        messageService.removeMessage(id);
    }


    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
        Message msg= messageService.getMessage(id);
        String strSelf = getUriForSelf(uriInfo, msg);
        String strProfile = getUriForProfile(uriInfo, msg);
        String strComments = getUriForComments(uriInfo, msg);
        msg.addLink(strSelf,"self");
        msg.addLink(strProfile,"profile");
        msg.addLink(strComments,"comments");
        return msg;
    }


    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }

    public String getUriForSelf(@Context UriInfo uriInfo, Message msg) {
        return uriInfo.getBaseUriBuilder()   //getting "http://localhost:8020/messenger/rest"
                .path(MessageResource.class)    //getting the class level annotation of the MessageResource class i.e "messages"
                .path(Long.toString(msg.getId()))   //getting the id of the message from the URL
                .build()
                .toString();
    }


    public String getUriForProfile(UriInfo uriInfo, Message msg) {
        String uri=uriInfo.getBaseUriBuilder()
                .path(ProfileResources.class)
                .path(msg.getAuthor())
                .build()
                .toString();
        return uri;
    }

    public String getUriForComments(UriInfo uriInfo, Message msg) {
        String uri=uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class,"getCommentResource") //getting the method level annotation of "getCommentResource" method
                .path(CommentResource.class)
                .resolveTemplate("messageId",msg.getId()) //replacing the "messageId" with the dynamic ID that we send in the URL
                .build()
                .toString();
        return uri;
    }
}