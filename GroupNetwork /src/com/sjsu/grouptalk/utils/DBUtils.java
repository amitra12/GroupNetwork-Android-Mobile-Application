package com.sjsu.grouptalk.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sjsu.grouptalk.common.Group;
import com.sjsu.grouptalk.common.GroupMember;
import com.sjsu.grouptalk.common.Location;

public class DBUtils {
	private static String HOST = "sensor-database.ckxiqr8myjaj.us-west-2.rds.amazonaws.com";//"127.0.0.1";
	private static String PORT = "3306";
	private static String USERNAME = "root";
	private static String PASSWORD = "mysqlroot";
	private static String SCHEMA_NAME = "group_talk_db";
	
	public static List<Group> getGroups(String phoneNumber) {
		List<Group> groups = new ArrayList<Group>();
		Connection connection = getDatabaseConnection();
		PreparedStatement stmt = null;
		String query = "select user_name, group_id, group_name, is_owner, accepted_on, denied_on, left_on, row_updated_on from user_groups where phone_number = ? order by row_updated_on desc";
		
		try {
			connection = getDatabaseConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, phoneNumber);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int groupId = rs.getInt("group_id");
				String userName = rs.getString("user_name");
				String groupName = rs.getString("group_name");
				Timestamp acceptedOn = rs.getTimestamp("accepted_on");
				Timestamp deniedOn = rs.getTimestamp("denied_on");
				Timestamp leftOn = rs.getTimestamp("left_on");
				Timestamp updatedOn = rs.getTimestamp("row_updated_on");

				Group group = new Group(groupId, groupName, acceptedOn, deniedOn, leftOn , updatedOn );
				groups.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// DO NOTHING
			}
		}

		return groups;
		
	}
	
	public static List<GroupMember> getGroupMembersWitLatestLocations(int groupId, String memberPhone) {
		List<GroupMember> groupMembers = new ArrayList<GroupMember>();
		Connection connection = getDatabaseConnection();
		PreparedStatement stmt = null;
		String query = "select group_id, group_name, l.phone_number, user_name, "
				+ "latitude, longitude from locations l, user_groups ug "
				+ "where ug.group_id = ? and l.phone_number = ug.phone_number and l.phone_number != ?";
		p(query);
		
		try {
			connection = getDatabaseConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, groupId);
			stmt.setString(2, memberPhone);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int grpId = rs.getInt("group_id");
				String userName = rs.getString("user_name");
				String groupName = rs.getString("group_name");
				String phoneNumber = rs.getString("phone_number");
				String latitude = rs.getString("latitude");
				String longitude = rs.getString("longitude");
				
				GroupMember member = new GroupMember(userName, phoneNumber);
				Location l = new Location(latitude, longitude, null);
				member.setLocation(l);
				groupMembers.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// DO NOTHING
			}
		}

		return groupMembers;
	}
	
	public static GroupMember getLocation(String userId) {
		Connection connection = getDatabaseConnection();
		PreparedStatement stmt = null;
		String query = "select l.phone_number, "
				+ "latitude, longitude from locations l "
				+ "where l.phone_number = ?";
		p(query);
		GroupMember member = null;
		try {
			connection = getDatabaseConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String phoneNumber = rs.getString("phone_number");
				String latitude = rs.getString("latitude");
				String longitude = rs.getString("longitude");
				
				member = new GroupMember(phoneNumber, phoneNumber);
				Location l = new Location(latitude, longitude, null);
				member.setLocation(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// DO NOTHING
			}
		}

		return member;
	}
	
	

	public synchronized static boolean updateTokenForUser(String userId, String token) {
		String query = "";
		Statement stmt = null;
		Connection connection = null;
		boolean result = false;
		
		 try {
				connection = getDatabaseConnection();
		        
				query = "update users set gcm_token = '"+token+"' where phone_number = '"+userId+"'";
				
		        stmt = connection.createStatement();
				System.out.println("Query - " + query);
		        stmt.executeUpdate(query);
		        System.out.println("Updated token for user - " + userId );
		        result = true;
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    } finally {
		    	try {
		        if (stmt != null) { stmt.close(); }
		        if(connection != null) { connection.close(); }
		    	} catch(Exception e) { 
		    		// DO NOTHING
		    	}
		    }
		 return result;
	}
	
	
	public synchronized static boolean saveLocationOfMember(String userId, String latitude, String longitude) {
		String query = "";
		Statement stmt = null;
		Connection connection = null;
		boolean result = false;
		
		 try {
				connection = getDatabaseConnection();
		        
		        if ( getLocation(userId) == null ) {
					query = "insert into locations values ('"+userId+"', '"+latitude+"', '"+longitude+"', NOW() )";	
					
				} else {
					query = "update locations set latitude = '"+latitude+"', longitude = '"+longitude+"' , row_updated_on = NOW() where phone_number = '"+userId+"'";
				}
		        stmt = connection.createStatement();
				System.out.println("Query - " + query);
		        stmt.executeUpdate(query);
		        System.out.println("Inserted bus location in database" );
		        result = true;
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    } finally {
		    	try {
		        if (stmt != null) { stmt.close(); }
		        if(connection != null) { connection.close(); }
		    	} catch(Exception e) { 
		    		// DO NOTHING
		    	}
		    }
		 return result;
	}
	
	public static List<GroupMember>  getAllRelatedContactsForPanic(String userId) {
		List<GroupMember> groupMembers = new ArrayList<GroupMember>();
		Connection connection = getDatabaseConnection();
		PreparedStatement stmt = null;
		String query = "select distinct ug.user_name, ug.phone_number, u.token from user_groups ug, users u "
				+ "where ug.phone_number <> ? and ug.phone_number = u.phone_number "
				+ "and ug.group_id in (select group_id from user_groups where phone_number = ?)";
		
		p(query);
		
		try {
			connection = getDatabaseConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, userId);
			stmt.setString(2, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString("user_name");
				String phoneNumber = rs.getString("phone_number");
				String token = rs.getString("token");
				
				GroupMember member = new GroupMember(userName, phoneNumber);
				member.setGcmToken(token);
				groupMembers.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// DO NOTHING
			}
		}

		return groupMembers;
	}
	
	private static Connection getDatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + SCHEMA_NAME + "?useSSL=false",
					USERNAME, PASSWORD);

			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	}	

	
	private static void p (String str) {
		System.out.println(str);
	}
}
