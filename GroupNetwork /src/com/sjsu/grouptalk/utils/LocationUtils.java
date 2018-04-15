package com.sjsu.grouptalk.utils;

public class LocationUtils {

	
	public static boolean saveLocationOfMember(String memberPhone, String latitude, String longitude) {
		return DBUtils.saveLocationOfMember(memberPhone, latitude, longitude);
	}
}
