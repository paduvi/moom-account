/**
 * @author chotoxautinh
 *
 * Apr 2, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.chotoxautinh.server.dao.CounterDao;
import com.chotoxautinh.server.dao.UserDao;
import com.chotoxautinh.server.model.User;
import com.chotoxautinh.server.repository.UserRepository;
import com.google.common.collect.Lists;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Component
public class UserDaoImpl implements UserDao{
	
	public static final String collectionName = "users";
	@Autowired
	private CounterDao counterDao;

	@Autowired
	private UserRepository repository;

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#createUser(com.chotoxautinh.server.model.User)
	 */
	@Override
	public boolean createUser(User user) {
		try {
			if (repository.findByUsername(user.getUsername()) != null)
				return false;
			user.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
			repository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#updateUser(com.chotoxautinh.server.model.User)
	 */
	@Override
	public boolean updateUser(User user) {
		try {
			repository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#removeUser(java.lang.String)
	 */
	@Override
	public boolean removeUser(String id) {
		try {
			User existingEmail = findUserById(id);

			if (existingEmail == null) {
				return false;
			}
			repository.delete(existingEmail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#removeUser(com.chotoxautinh.server.model.User)
	 */
	@Override
	public boolean removeUser(User user) {
		return removeUser(user.getId());
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findUserById(java.lang.String)
	 */
	@Override
	public User findUserById(String id) {
		return repository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findUserByUsername(java.lang.String)
	 */
	@Override
	public User findUserByUsername(String name) {
		return repository.findByUsername(name);
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findUser(com.mysema.query.types.Predicate)
	 */
	@Override
	public User findUser(Predicate predicate) {
		return repository.findOne(predicate);
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findAllUsers()
	 */
	@Override
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findAllUsers(com.mysema.query.types.Predicate)
	 */
	@Override
	public List<User> findAllUsers(Predicate predicate) {
		return Lists.newLinkedList(repository.findAll(predicate));
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findAllUsers(com.mysema.query.types.Predicate, com.mysema.query.types.OrderSpecifier[])
	 */
	@Override
	public List<User> findAllUsers(Predicate predicate, OrderSpecifier<?>... orders) {
		return Lists.newLinkedList(repository.findAll(predicate, orders));
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findUsersByPage(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<User> findUsersByPage(Pageable page) {
		return repository.findAll(page);
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#findUsersByPage(com.mysema.query.types.Predicate, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<User> findUsersByPage(Predicate predicate, Pageable page) {
		return repository.findAll(predicate, page);
	}

	/* (non-Javadoc)
	 * @see com.chotoxautinh.server.dao.UserDao#count(com.mysema.query.types.Predicate)
	 */
	@Override
	public Long count(Predicate predicate) {
		return repository.count(predicate);
	}

}
