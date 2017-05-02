package org.arya.messenger.resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.arya.messenger.model.Comment;
import org.arya.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CommentResource {
    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId){
        return commentService.getAllComment(messageId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId, Comment comment){
        return commentService.addComment(messageId, comment);
    }


    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long id,
                                 Comment comment){
        comment.setId(id);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void removeComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long id,
                              long commentId){
        commentService.removeComment(messageId, commentId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long id,
                              long commentId){
        return commentService.getComment(messageId, commentId);
    }
}