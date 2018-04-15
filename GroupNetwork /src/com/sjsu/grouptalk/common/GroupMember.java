package com.sjsu.grouptalk.common;

public class GroupMember {

	private String groupmemberName;
	private String groupMemberPhoneNumber;
	private Location location;
	private String gcmToken;
	
	public GroupMember(String groupmemberName, String groupMemberPhoneNumber) {
		super();
		this.groupmemberName = groupmemberName;
		this.groupMemberPhoneNumber = groupMemberPhoneNumber;
	}
	public GroupMember() {
		super();
	}
	public String getGroupmemberName() {
		return groupmemberName;
	}
	public void setGroupmemberName(String groupmemberName) {
		this.groupmemberName = groupmemberName;
	}
	public String getGroupMemberPhoneNumber() {
		return groupMemberPhoneNumber;
	}
	public void setGroupMemberPhoneNumber(String groupMemberPhoneNumber) {
		this.groupMemberPhoneNumber = groupMemberPhoneNumber;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getGcmToken() {
		return gcmToken;
	}
	public void setGcmToken(String gcmToken) {
		this.gcmToken = gcmToken;
	}
	
	
}
