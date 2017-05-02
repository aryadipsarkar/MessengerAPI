package org.arya.messenger.database;

import java.util.HashMap;

import org.arya.messenger.model.Message;
import org.arya.messenger.model.Profile;

public class DatabaseClass {
    /**
     * The class acts a a stub for database
     * a static class which acts a database where messages are maps to corresponding id
     * any class in the application can access the entire map of messages/profiles by calling the static methods
     * Ideally this should be JDBC or Hibernate class which connect to the database
     * But here we are storing the data as a static variable like a database so that every object in the
     * package can access this.
     *
     * So basically this acts as a database
     */


    private static HashMap<Long, Message> messages= new HashMap<Long, Message>();
    private static HashMap<String, Profile> profiles= new HashMap<String, Profile>();


    public static HashMap<Long, Message> getMessages(){
        return messages;
    }

    public static HashMap<String, Profile> getProfiles(){
        return profiles;
    }
}