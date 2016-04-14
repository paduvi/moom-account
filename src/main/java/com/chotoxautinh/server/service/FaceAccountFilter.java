/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.chotoxautinh.server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.chotoxautinh.server.dao.GroupDao;
import com.chotoxautinh.server.model.Group;
import com.chotoxautinh.server.model.QFaceAccount;
import com.chotoxautinh.server.model.QGroup;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

/**
 * Author : tungtt Mar 28, 2016
 */
@Component
public class FaceAccountFilter {

	@Autowired
	private GroupDao groupDao;

	public static final int LATEST = -3;
	public static final int NONE = -2;
	public static final int HAVE = -1;
	
	public Predicate build(Integer group, String groupName) {
		return build(null, null, null, null, group, groupName);
	}
	
	public Predicate build(String id, String email, String password, String phone) {
		return build(id, email, password, phone, null, null);
	}
	
	public Predicate build(String id, String email, String password, String phone, Integer group) {
		return build(id, email, password, phone, group, null);
	}

	public Predicate build(String id, String email, String password, String phone, Integer group, String groupName) {
		BooleanBuilder builder = new BooleanBuilder();
		if (id != null && !id.isEmpty())
			builder.and(QFaceAccount.faceAccount.id.like(toAlias(id)));
		if (email != null && !email.isEmpty())
			builder.and(QFaceAccount.faceAccount.email.like(toAlias(email)));
		if (password != null && !password.isEmpty())
			builder.and(QFaceAccount.faceAccount.password.like(toAlias(password)));
		if (phone != null && !phone.isEmpty())
			builder.and(QFaceAccount.faceAccount.phone.like(toAlias(phone)));
		if (group != null) {
			List<String> allGroupId = groupDao.findAllGroups().stream().map(Group::getId).collect(Collectors.toList());
			switch (group) {
			case LATEST:
				List<Group> groupPage = groupDao
						.findGroupsByPage(new PageRequest(0, 1, Direction.DESC, "lastExecution")).getContent();
				if (!groupPage.isEmpty()) {
					builder.and(QFaceAccount.faceAccount.group.eq(groupPage.get(0).getId()));
				}
				break;
			case NONE:
				builder.and(QFaceAccount.faceAccount.group.notIn(allGroupId));
				break;
			case HAVE:
				builder.and(QFaceAccount.faceAccount.group.in(allGroupId));
				break;
			default:
				builder.and(QFaceAccount.faceAccount.group.eq(String.valueOf(group)));
				break;
			}
		}
		if (groupName != null && !groupName.isEmpty()) {
			Predicate predicate = QGroup.group.name.like(toAlias(groupName));
			List<String> allGroupId = groupDao.findAllGroups(predicate).stream().map(Group::getId)
					.collect(Collectors.toList());
			builder.and(QFaceAccount.faceAccount.group.in(allGroupId));
		}
		return builder.getValue();
	}

	private static String toAlias(String input) {
		return "%" + input + "%";
	}

}