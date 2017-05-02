package org.arya.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.arya.messenger.database.DatabaseClass;
import org.arya.messenger.exception.DataNotFoundException;
import org.arya.messenger.model.Message;

public class MessageService {
    //getting all the messages in a hashmap from the database
    private HashMap<Long, Message> messages= DatabaseClass.getMessages();

    //adding few messages to the database
    public MessageService(){
        messages.put(1L, new Message(1L,"Hello JAX-RS", "Arya"));
        messages.put(2L, new Message(2L,"Hello Jersey", "Kathy"));
        messages.put(3L, new Message(3L,"Hello WebSer", "Jake"));
    }

    //getting all the messages
    public List<Message> getAllMessages(){
        ArrayList<Message> list= new ArrayList<Message>();
        list.addAll(messages.values());
        return list;

    }

    //getting the messages by year
    public ArrayList<Message> getAllMessagesByYear(int year){
        ArrayList<Message> messageForYear= new ArrayList<Message>();
        Calendar cal= Calendar.getInstance();
        for (Message message:messages.values()){
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR)==year){
                messageForYear.add(message);
            }
        }
        return messageForYear;
    }

    //getting the messages by pagination
    public List<Message> getAllMessagePaginated(int start,int size){
        ArrayList<Message> list= new ArrayList<Message>(messages.values());
        if (start+size>list.size())return new ArrayList<Message>();
        return list.subList(start, start+size);
    }

    //getting a particular message
    public Message getMessage(long id){
        Message msg= messages.get(id);
        if (msg==null){
            throw new DataNotFoundException("Message with "+id+" not found");
        }
        return msg;
    }

    //adding a message
    public Message addMessage(Message message){
        message.setId(messages.size()+1);
        messages.put(message.getId(), message);
        return message;
    }

    //updating a message
    public Message updateMessage(Message message){
        if (message.getId()<=0)return null;
        messages.put(message.getId(), message);
        return message;
    }

    //removing a message
    public Message removeMessage(long id){
        Message msg= messages.remove(id);
        return msg;
    }

}