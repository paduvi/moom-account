/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.chotoxautinh.server.dao.CounterDao;
import com.chotoxautinh.server.dao.GroupDao;
import com.chotoxautinh.server.model.Group;
import com.chotoxautinh.server.repository.GroupRepository;
import com.chotoxautinh.server.service.CounterException;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Component
public class GroupDaoImpl implements GroupDao {

	static Logger log = LoggerFactory.getLogger(GroupDaoImpl.class);
	public static final String collectionName = "groups";
	@Autowired
	private CounterDao counterDao;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GroupRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#addGroup(com.chotoxautinh.model.Group)
	 */
	@Override
	public Group addGroup(Group group) {
		group.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
		return repository.save(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#updateGroup(com.chotoxautinh.model.Group)
	 */
	@Override
	public Group updateGroup(Group group) {
		return repository.save(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#incnAccounts(java.lang.String)
	 */
	@Override
	public Group incnAccounts(String id) {
		return changenAccounts(id, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#decnAccounts(java.lang.String)
	 */
	@Override
	public Group decnAccounts(String id) {
		return changenAccounts(id, -1);
	}

	private Group changenAccounts(String id, int number) {
		// get group id
		Query query = new Query(Criteria.where("_id").is(id));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("nAccounts", number);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		Group group = mongoTemplate.findAndModify(query, update, options, Group.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (group == null) {
			log.info("Unable to get group document for id : " + id);
		}
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#removeGroup(java.lang.String)
	 */
	@Override
	public void removeGroup(String id) {
		Group existingGroup = repository.findById(id);

		if (existingGroup == null) {
			throw new CounterException("Unable to get group for id : " + id);
		}
		repository.delete(existingGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#removeGroup(com.chotoxautinh.model.Group)
	 */
	@Override
	public void removeGroup(Group group) {
		repository.delete(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findGroupById(java.lang.String)
	 */
	@Override
	public Group findGroupById(String id) {
		return repository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findGroupByName(java.lang.String)
	 */
	@Override
	public Group findGroupByName(String name) {
		return repository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#findGroup(com.mysema.query.types.Predicate)
	 */
	@Override
	public Group findGroup(Predicate predicate) {
		return repository.findOne(predicate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findAllGroups()
	 */
	@Override
	public List<Group> findAllGroups() {
		return repository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findAllGroups(com.mysema.query.types.
	 * Predicate)
	 */
	@Override
	public Iterable<Group> findAllGroups(Predicate predicate) {
		return repository.findAll(predicate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findAllGroups(com.mysema.query.types.
	 * Predicate, com.mysema.query.types.OrderSpecifier[])
	 */
	@Override
	public Iterable<Group> findAllGroups(Predicate predicate, OrderSpecifier<?>... orders) {
		return repository.findAll(predicate, orders);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#findGroupsByPage(org.springframework.data.
	 * domain.Pageable)
	 */
	@Override
	public Page<Group> findGroupsByPage(Pageable page) {
		return repository.findAll(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#findGroupsByPage(com.mysema.query.types.
	 * Predicate, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Group> findGroupsByPage(Predicate predicate, Pageable page) {
		return repository.findAll(predicate, page);
	}

}
