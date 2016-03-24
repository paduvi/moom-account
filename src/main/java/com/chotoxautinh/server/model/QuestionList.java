/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.chotoxautinh.server.model;

import java.util.ArrayList;

import com.chotoxautinh.server.model.QuestionList.SecurityQuestion;

/**
 * Author : tungtt Mar 24, 2016
 */
public class QuestionList extends ArrayList<SecurityQuestion> {
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
}
