/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
@Document(collection = "groups")
public class Group {
	@Id
	private String id;
	
	private String name;
	private int nAccounts;
	private long lastExecution;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLastExecution() {
		return lastExecution;
	}

	public void setLastExecution(long lastExecution) {
		this.lastExecution = lastExecution;
	}

	public int getnAccounts() {
		return nAccounts;
	}

	public void setnAccounts(int nAccounts) {
		this.nAccounts = nAccounts;
	}
}
