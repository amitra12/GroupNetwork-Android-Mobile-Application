package com.sjsu.grouptalk.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sjsu.grouptalk.common.Group;
import com.sjsu.grouptalk.common.GroupMember;
import com.sjsu.grouptalk.common.Location;

public class GroupUtils {

	public static List<Group> getGroups(String memberPhone) {
		
		
		//Using database find groups of the given user
		return DBUtils.getGroups(memberPhone);

		// return dummy data for now 
//		ArrayList<Group> groups = new ArrayList<Group>();
//		Group group1 = new Group(1, "group 1");
//		Group group2 = new Group(2, "group 2");
//		Group group3 = new Group(3, "group 2");
//		
//		groups.add(group1);
//		groups.add(group2);
//		groups.add(group3);
//		
//		return groups;
	}
	
	
	public static List<GroupMember> getGroupMembersWitLatestLocations(int groupId, String memberPhone) {
		
		
		// Using database find members and locations for given group 
		return DBUtils.getGroupMembersWitLatestLocations(groupId, memberPhone);
		
		/*
		ArrayList<GroupMember> groupMembers = new ArrayList<GroupMember>();
		GroupMember member1 = new GroupMember("Poonam", "P1234");
		GroupMember member2 = new GroupMember("Siddhi", "P7850");
		Location loc1 = new Location("","", new Date());

		member1.setLocation(loc1);
		member2.setLocation(loc1);
		
		groupMembers.add(member1);
		groupMembers.add(member2);

		return groupMembers;
		*/
	}
	
	public static List<GroupMember> getAllPanicGroupMembers(String userId) {
		return DBUtils.getAllRelatedContactsForPanic(userId);	
	}
}
