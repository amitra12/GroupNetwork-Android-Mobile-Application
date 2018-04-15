package com.sjsu.grouptalk.common;

import java.sql.Timestamp;

public class Group {

	private int id;
	private String groupName;
	Timestamp acceptedOn;
	Timestamp deniedOn;
	Timestamp leftOn;
	Timestamp rowUpdatedON;
	

	public Group() {
		
	}
	
	public Group(int id, String groupName) {
		super();
		this.id = id;
		this.groupName = groupName;
	}

	public Group(int id, String groupName, Timestamp acceptedOn, Timestamp deniedOn, Timestamp leftOn,
			Timestamp rowUpdatedON) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.acceptedOn = acceptedOn;
		this.deniedOn = deniedOn;
		this.leftOn = leftOn;
		this.rowUpdatedON = rowUpdatedON;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getAcceptedOn() {
		return acceptedOn;
	}

	public void setAcceptedOn(Timestamp acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public Timestamp getDeniedOn() {
		return deniedOn;
	}

	public void setDeniedOn(Timestamp deniedOn) {
		this.deniedOn = deniedOn;
	}

	public Timestamp getLeftOn() {
		return leftOn;
	}

	public void setLeftOn(Timestamp leftOn) {
		this.leftOn = leftOn;
	}

	public Timestamp getRowUpdatedON() {
		return rowUpdatedON;
	}

	public void setRowUpdatedON(Timestamp rowUpdatedON) {
		this.rowUpdatedON = rowUpdatedON;
	}
	
}
