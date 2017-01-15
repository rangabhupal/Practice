package com.ranga.webservices.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ranga.webservices.DataService;
import com.ranga.webservices.resources.data.Profile;

@Path("/profile")
public class ProfileResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles(@QueryParam("byUsername") String byUsername) {
		List<Profile> pList = new ArrayList<Profile>();

		if (byUsername == null || byUsername.isEmpty()) {
			pList = DataService.getInstance().getProfiles();
		} else {
			List<Profile> list = DataService.getInstance().getProfiles();
			for (int i=0;i<list.size();i++) {
				if (list.get(i).getUsername().contains(byUsername)) {
					pList.add(list.get(i));
				}
			}
		}
		return pList;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{profileId}")
	public Profile getProfile(@PathParam("profileId") String profileId) {
		return DataService.getInstance().getProfileById(profileId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile profileObj) {
		Profile profile = DataService.getInstance().addProfile(profileObj);
		return profile;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile updateProfile(Profile profileObj) {
		Profile profile = DataService.getInstance().updateProfile(profileObj);
		return profile;
	}

	@DELETE
	@Path("/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Profile updateProfile(@PathParam("profileId") String profileId) {
		Profile profile = DataService.getInstance()
				.deleteProfileById(profileId);
		return profile;
	}
}
