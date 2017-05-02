package org.arya.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {
    private long id;
    private String message;
    private Date created;
    private String author;
    //every message has a bunch of comments with message-id and the comment
    private Map<Long, Comment> comments= new HashMap<Long, Comment>();
    private List<Link> links= new ArrayList<Link>();

    public Message() {
        super();
    }
    public Message(long id, String message,String author) {
        super();
        this.id = id;
        this.message = message;
        this.created = new Date();//to make it dynamic
        this.author = author;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * we don't want list of all comments to show up when the message instance is pulled up
     * We want the comment list to be ignored when the message list is being converted to XML/JSON
     * The way to do that is by @XmlTransient. This marks the code block to be ignored for XML/JSON Conversion
     * @return
     */
    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }
    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }
    public List<Link> getLinks() {
        return links;
    }
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url,String rel){
        Link link= new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

}