/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
  
	public final static int STAFF = 0;
	public final static int ADMIN = 1;

	private String username;
	private String fullname;
	private String password;
	private int role = STAFF;

	public User() {

	}

	public User(String user, String pass) {
		this.username = user;
		this.password = pass;
	}

	@XmlElement
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}

