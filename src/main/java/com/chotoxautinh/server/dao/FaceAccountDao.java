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

public interface FaceAccountDao {
	boolean addFaceAccount(FaceAccount faccount);

	boolean updateFaceAccount(FaceAccount faccount);
	
	boolean addFaceAccountToGroup(FaceAccount faccount, String groupId);
	
	boolean addFaceAccountToGroup(FaceAccount faccount, Group group);
	
	boolean removeFaceAccount(String id);
	
	boolean removeFaceAccount(FaceAccount faccount);

	FaceAccount findFaceAccountById(String id);

	FaceAccount findFaceAccount(Predicate predicate);

	List<FaceAccount> findAllFaceAccounts();
	
	List<FaceAccount> findAllFaceAccounts(Predicate predicate);
	
	List<FaceAccount> findAllFaceAccounts(Predicate predicate, OrderSpecifier<?>... orders);
	
	Page<FaceAccount> findFaceAccountsByPage(Pageable page);

	Page<FaceAccount> findFaceAccountsByPage(Predicate predicate, Pageable page);
	
	Long count(Predicate predicate);
}
