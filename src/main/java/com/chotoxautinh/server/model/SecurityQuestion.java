/**
 * @author chotoxautinh
 *
 * Mar 25, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.model;

import javax.persistence.Entity;

@Entity
public class SecurityQuestion {
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
