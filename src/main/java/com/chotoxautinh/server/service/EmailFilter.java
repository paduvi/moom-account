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
		if (id != null)
			builder.and(QEmail.email.id.like(id));
		if (username != null)
			builder.and(QEmail.email.username.like(username));
		if (password != null)
			builder.and(QEmail.email.password.like(password));
		if (retrieveEmail != null)
			builder.and(QEmail.email.retrieveEmail.like(retrieveEmail));
		if (phone != null)
			builder.and(QEmail.email.phone.like(phone));
		if (birthday != null)
			builder.and(QEmail.email.birthday.eq(birthday));
	}

	public Predicate getPredicate() {
		return builder.getValue();
	}
}
