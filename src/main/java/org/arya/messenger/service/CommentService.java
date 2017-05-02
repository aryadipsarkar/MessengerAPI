package org.arya.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.arya.messenger.database.DatabaseClass;
import org.arya.messenger.model.Message;
import org.arya.messenger.model.Comment;

public class CommentService {
    private Map<Long,Message> messages= DatabaseClass.getMessages();

    public List<Comment> getAllComment(long messageId){
        Map<Long, Comment> comments=messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    public Comment getComment(long messageId, long commentId){
        Map<Long, Comment> comments=messages.get(messageId).getComments();
        Comment com= comments.get(commentId);
        return com;
    }

    public Comment addComment(long messageId, Comment comment){
        Map<Long, Comment> comments=messages.get(messageId).getComments();
        comment.setId(comments.size()+1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment){
        Map<Long, Comment> comments=messages.get(messageId).getComments();
        if (comment.getId()>=0)return null;
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId){
        Map<Long, Comment> comments=messages.get(messageId).getComments();
        Comment com= comments.remove(commentId);
        return com;
    }
}