package com.chotoxautinh.model;

import java.util.Date;
import java.util.List;

public class Group {
	private int id;
	private String groupName;
	private List<FaceAccount> faceAccounts;
	private Date lastExecution;
	
	public Group() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<FaceAccount> getFaceAccounts() {
		return faceAccounts;
	}

	public void setFaceAccounts(List<FaceAccount> faceAccounts) {
		this.faceAccounts = faceAccounts;
	}
	
	public boolean containAccount(FaceAccount faceAccount){
		return faceAccounts.contains(faceAccount);
	}

	public Date getLastExecution() {
		return lastExecution;
	}

	public void setLastExecution(Date lastExecution) {
		this.lastExecution = lastExecution;
	}
}
