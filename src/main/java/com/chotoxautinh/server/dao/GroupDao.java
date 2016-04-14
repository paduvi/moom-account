/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chotoxautinh.server.model.FaceAccount;
import com.chotoxautinh.server.model.Group;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public interface GroupDao {
	boolean addGroup(Group group);

	boolean updateGroup(Group group);

	/**
	 * Increase number of account in group by 1
	 * 
	 * @param id
	 *            - group index
	 * @return
	 */
	boolean incnAccounts(String id);

	/**
	 * Decrease number of account in group by 1
	 * 
	 * @param id
	 *            - group index
	 * @return
	 */
	boolean decnAccounts(String id);

	boolean removeGroup(String id);

	boolean removeGroup(Group group);
	
	boolean removeAccount(String id, FaceAccount account);
	
	Group findGroupById(String id);

	Group findGroupByName(String name);

	Group findGroup(Predicate predicate);

	List<Group> findAllGroups();

	List<Group> findAllGroups(Predicate predicate);

	List<Group> findAllGroups(Predicate predicate, OrderSpecifier<?>... orders);

	Page<Group> findGroupsByPage(Pageable page);

	Page<Group> findGroupsByPage(Predicate predicate, Pageable page);

	Long count(Predicate predicate);
}
