package com.chotoxautinh.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;

/**
 * @author chotoxautinh
 *
 * http://chotoxautinh.com/
 * Mar 23, 2016
 */
@QueryEntity
@Document(collection = "emails")
public class Email {
	class SecurityQuestion {
		private String question;
		private String answer;

		public SecurityQuestion(String question, String answer) {
			this.question = question;
			this.answer = answer;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}
	}

	@Id
	private String id;

	private String username;
	private String password;
	private String phone;
	private String retrieveEmail;
	private Date birthday;
	private List<SecurityQuestion> questions;

	public Email() {
		// TODO Auto-generated constructor stub
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

	public void addQuestion(SecurityQuestion question) {
		questions.add(question);
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
