package com.sjsu.grouptalk.common;

import java.util.Date;

public class Location {
	String latitude;
	String longitude;
	Date asOf;
	
	public Location(String latitude, String longitude, Date asOf) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.asOf = asOf;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getAsOf() {
		return asOf;
	}

	public void setAsOf(Date asOf) {
		this.asOf = asOf;
	}
	
	

}
