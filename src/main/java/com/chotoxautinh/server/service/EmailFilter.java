/**
 * @author chotoxautinh
 *
 * Mar 25, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.service;

import com.chotoxautinh.server.model.QEmail;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

public class EmailFilter {
	private BooleanBuilder builder = new BooleanBuilder();

	public EmailFilter(String id, String username, String password, String retrieveEmail, String phone, Long birthday) {
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
		if (birthday != null)
			builder.and(QEmail.email.birthday.eq(birthday));
	}

	public Predicate getPredicate() {
		return builder.getValue();
	}

	private String toAlias(String input) {
		return "%" + input + "%";
	}
}
