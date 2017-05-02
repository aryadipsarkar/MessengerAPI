package org.arya.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
    /**
     * very similar to the message model object.
     * Instead of storing the data whatever is entered in this class, we create a database class which is
     * basically static class which will map the user id with the user, profile id with profile etc.
     * So any
     */

    private long id;
    private String profilename;
    private String firstname;
    private String lastname;
    private Date created;

    public Profile() {
        super();
    }

    public Profile(long id, String profilename, String firstname, String lastname) {
        super();
        this.id = id;
        this.profilename = profilename;
        this.firstname = firstname;
        this.lastname = lastname;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}