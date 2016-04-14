/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
@Document(collection = "emails")
public class Email {

	@Id
	private String id;

	private String username;
	private String password;
	private String phone;
	private String retrieveEmail;
	private String birthday;
	private List<SecurityQuestion> questions;

	public Email() {
		// TODO Auto-generated constructor stub
	}

	public Email(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Email(String username, String password, String retrieveEmail) {
		this.username = username;
		this.password = password;
		this.retrieveEmail = retrieveEmail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRetrieveEmail() {
		return retrieveEmail;
	}

	public void setRetrieveEmail(String retrieveEmail) {
		this.retrieveEmail = retrieveEmail;
	}

	public List<SecurityQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SecurityQuestion> questions) {
		this.questions = questions;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}
