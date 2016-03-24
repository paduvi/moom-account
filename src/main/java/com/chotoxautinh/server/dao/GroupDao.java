/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chotoxautinh.server.model.Group;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public interface GroupDao {
	Group addGroup(Group group);

	Group updateGroup(Group group);

	/**
	 * Increase number of account in group by 1
	 * 
	 * @param id
	 *            - group index
	 * @return
	 */
	Group incnAccounts(String id);

	/**
	 * Decrease number of account in group by 1
	 * 
	 * @param id
	 *            - group index
	 * @return
	 */
	Group decnAccounts(String id);

	void removeGroup(String id);

	void removeGroup(Group group);

	Group findGroupById(String id);

	Group findGroupByName(String name);

	Group findGroup(Predicate predicate);

	List<Group> findAllGroups();

	Iterable<Group> findAllGroups(Predicate predicate);

	Iterable<Group> findAllGroups(Predicate predicate, OrderSpecifier<?>... orders);

	Page<Group> findGroupsByPage(Pageable page);

	Page<Group> findGroupsByPage(Predicate predicate, Pageable page);
}
