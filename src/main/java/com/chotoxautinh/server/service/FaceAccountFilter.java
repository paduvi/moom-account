/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.chotoxautinh.server.service;

import com.chotoxautinh.server.model.QFaceAccount;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

/**
 *  Author : tungtt         
 * Mar 28, 2016
 */
public class FaceAccountFilter {
	private BooleanBuilder builder = new BooleanBuilder();

	public FaceAccountFilter(String id, String email, String password, String phone, String group) {
		if (id != null && !id.isEmpty())
			builder.and(QFaceAccount.faceAccount.id.like(toAlias(id)));
		if (email != null && !email.isEmpty())
			builder.and(QFaceAccount.faceAccount.email.like(toAlias(email)));
		if (password != null && !password.isEmpty())
			builder.and(QFaceAccount.faceAccount.password.like(toAlias(password)));
		if (phone != null && !phone.isEmpty())
			builder.and(QFaceAccount.faceAccount.phone.like(toAlias(phone)));
		if (group != null && !group.isEmpty())
			builder.and(QFaceAccount.faceAccount.group.eq(group));
	}

	public Predicate getPredicate() {
		return builder.getValue();
	}

	private String toAlias(String input) {
		return "%" + input + "%";
	}
}
