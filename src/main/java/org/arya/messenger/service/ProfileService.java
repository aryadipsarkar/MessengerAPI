package org.arya.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.arya.messenger.database.DatabaseClass;
import org.arya.messenger.model.Profile;
public class ProfileService {
    //getting all the messages in a HashMap from the database
    private HashMap<String, Profile> profiles= DatabaseClass.getProfiles();

    public ProfileService(){
        profiles.put("Arya", new Profile(1L,"Arya", "Aryadip","Sarkar"));
        profiles.put("Kathy", new Profile(2L,"Kathy", "Katherine","Jones"));
        profiles.put("Jake", new Profile(3L,"Jake", "Jackson","Devost"));
    }

    //getting all the messages
    public ArrayList<Profile> getAllProfiles(){
        ArrayList<Profile> list= new ArrayList<Profile>();
        list.addAll(profiles.values());
        return list;

    }

    //getting a particular message
    public Profile getProfile(String profileName){
        Profile prof= profiles.get(profileName);
        return prof;
    }

    //adding a message
    public Profile addProfile(Profile profile){
        profile.setId(profiles.size()+1);
        profiles.put(profile.getProfilename(), profile);
        return profile;
    }

    //updating a message
    public Profile updateProfile(Profile profile){
        if (profile.getProfilename().length()==0)
            return null;
        profiles.put(profile.getProfilename(), profile);
        return profile;
    }

    //removing a message
    public Profile removeProfile(String profileName){
        Profile prof= profiles.remove(profileName);
        return prof;
    }
}