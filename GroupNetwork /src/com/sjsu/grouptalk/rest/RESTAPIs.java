package com.sjsu.grouptalk.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sjsu.grouptalk.common.Group;
import com.sjsu.grouptalk.common.GroupMember;
import com.sjsu.grouptalk.utils.DBUtils;
import com.sjsu.grouptalk.utils.GroupUtils;
import com.sjsu.grouptalk.utils.LocationUtils;
import com.sjsu.grouptalk.utils.NotificationUtils;

@Path("/")
public class RESTAPIs {

	// application URL is like http://localhost:8085/GroupNetwork/groups/123
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/groups/{userPhone}")
	public List<Group> getGroupsForUser(@PathParam("userPhone") String userPhone) {
		List<Group> groups = GroupUtils.getGroups(userPhone);
		 
		return groups;
	}
	
	// appliction URL is like http://localhost:8085/GroupNetwork/group-members/location/1/6502555033
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/group-members/location/{groupId}/{userPhone}")
	public List<GroupMember> getGroupMembersWithLocation(@PathParam("groupId") int groupId, @PathParam("userPhone") String userPhone) {
		List<GroupMember> membersWithLocation = GroupUtils.getGroupMembersWitLatestLocations(groupId, userPhone);
		return membersWithLocation;
	}
	
	// appliction URL is like http://localhost:8085/GroupNetwork/panic-contacts/6502555033
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/panic-contacts/{userPhone}")
	public List<GroupMember> getAllPanicContacts(@PathParam("userPhone") String userPhone) {
		List<GroupMember> panicContacts = GroupUtils.getAllPanicGroupMembers(userPhone);
		return panicContacts;
	}
	
	// appliction URL is like http://localhost:8085/GroupNetwork/register-token/6502555033/ttttt
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register-token/{userPhone}/{token}")
	public boolean registerGCMToken(@PathParam("userPhone") String userId, @PathParam("token") String token) {
		return DBUtils.updateTokenForUser(userId, token);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/send-panic-notification/{userPhone}")
	public boolean sendPushNotification(@PathParam("userPhone") String userId) {
		return NotificationUtils.sendPanicNotificationFor(userId);
	}
		
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/group-members/location/{userPhone}/{latitude}/{longitude}")
	public boolean saveGroupMemberLocation(@PathParam("userPhone") String userPhone, 
			@PathParam("latitude") String latitude, @PathParam("longitude") String longitude) {

		return LocationUtils.saveLocationOfMember(userPhone, latitude, longitude);
	}
	
}