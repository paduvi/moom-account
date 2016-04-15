/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.chotoxautinh.server.service;

import org.springframework.stereotype.Component;

import com.chotoxautinh.server.model.QGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 *  Author : tungtt         
 * Apr 8, 2016
 */
@Component
public class GroupFilter {
	public Predicate build(String id, String name) {
		BooleanBuilder builder = new BooleanBuilder();
		if (id != null && !id.isEmpty())
			builder.and(QGroup.group.id.like(toAlias(id)));
		if (name != null && !name.isEmpty())
			builder.and(QGroup.group.name.like(toAlias(name)));
		return builder.getValue();
	}

	private static String toAlias(String input) {
		return "%" + input + "%";
	}
}
