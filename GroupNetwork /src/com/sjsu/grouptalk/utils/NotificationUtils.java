package com.sjsu.grouptalk.utils;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sjsu.grouptalk.common.GroupMember;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class NotificationUtils {

	
	public static boolean sendPanicNotificationFor(String userId) {
		List<GroupMember> panicContacts = DBUtils.getAllRelatedContactsForPanic(userId);
		PushNotification panicNotification = new PushNotification("Test title", "Test Body", null); 
		
		if(panicContacts != null)
		for(GroupMember member : panicContacts) {
			panicNotification.addRegistrationId(member.getGcmToken());
		}
		
		return sendNotifiationRequest(panicNotification);
	}
	
	
	public static boolean sendNotifiationRequest(PushNotification pushNotif) {
		
		String URL = "https://gcm-http.googleapis.com/gcm/send";
		String API_KEY = "AIzaSyCHTIXmUtr5zSJziw8hCg-fPhROKYETVMo";
		
		try {
	        Client client = Client.create();
	        
	        WebResource webResource = client.resource(URL);
	        
	        ClientResponse response = webResource.header("Content-Type","application/json")
	        		.header("Authorization","key="+API_KEY)
	        		.post(ClientResponse.class, pushNotif.toJSON());
	        
	        System.out.println("Status of Notification sent - " + response.getStatus());
	        
	        return (response.getStatus() == 200);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
}

class PushNotification {
	String title;
	String body;
	List<String> registration_ids;
	public PushNotification(String title, String body, List<String> registration_ids) {
		super();
		this.title = title;
		this.body = body;
		if(registration_ids == null)
			this.registration_ids = new ArrayList<String>();
		else
			this.registration_ids = registration_ids;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getRegistration_ids() {
		return registration_ids;
	}
	public void setRegistration_ids(List registration_ids) {
		this.registration_ids = registration_ids;
	}
	
	public void addRegistrationId(String id) {
		this.registration_ids.add(id);
	}
	
	public String toJSON() {
		String strIds = "";
		boolean first = true;
		for(String id : registration_ids) {
			if(!first)
				strIds += ",";
			strIds += "\""+id+"\"";
			first = true;
		}
		return "{\"data\":{\"title\":\""+title+"\"},\"body\":\""+body+"\"},\"registration_ids\":["
				+ strIds
				+ "]}";
	}
}
