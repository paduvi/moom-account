/**
 * @author chotoxautinh
 *
 * Mar 25, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.service;

import org.springframework.stereotype.Component;

import com.chotoxautinh.server.model.QEmail;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

@Component
public class EmailFilter {

	public Predicate build(String id, String username, String password, String retrieveEmail, String phone,
			String birthday) {
		BooleanBuilder builder = new BooleanBuilder();
		if (id != null && !id.isEmpty())
			builder.and(QEmail.email.id.like(toAlias(id)));
		if (username != null && !username.isEmpty())
			builder.and(QEmail.email.username.like(toAlias(username)));
		if (password != null && !password.isEmpty())
			builder.and(QEmail.email.password.like(toAlias(password)));
		if (retrieveEmail != null && !retrieveEmail.isEmpty())
			builder.and(QEmail.email.retrieveEmail.like(toAlias(retrieveEmail)));
		if (phone != null && !phone.isEmpty())
			builder.and(QEmail.email.phone.like(toAlias(phone)));
		if (birthday != null && !birthday.isEmpty())
			builder.and(QEmail.email.birthday.like(toAlias(birthday)));
		return builder.getValue();
	}

	private static String toAlias(String input) {
		return "%" + input + "%";
	}
}
