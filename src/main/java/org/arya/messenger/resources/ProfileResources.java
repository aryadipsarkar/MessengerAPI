package org.arya.messenger.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.arya.messenger.model.Profile;
import org.arya.messenger.service.ProfileService;

@Path("/secured/profiles")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class ProfileResources {
    private ProfileService profileService = new ProfileService();

    @GET
    public ArrayList<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }


    @POST
    public Profile addProfile(Profile profile) {
        Profile prof = profileService.addProfile(profile);
        return prof;
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        Profile prof = profileService.getProfile(profileName);
        return prof;
    }

    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfilename(profileName);
        Profile prof = profileService.updateProfile(profile);
        return prof;
    }

    @DELETE
    @Path("/{profileName}")
    public void removeProfile(@PathParam("profileName") String profileName) {
        profileService.removeProfile(profileName);
    }
}