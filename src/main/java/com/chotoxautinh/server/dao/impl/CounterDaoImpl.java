/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chotoxautinh.server.dao.CounterDao;
import com.chotoxautinh.server.repository.CounterRepository;
import com.chotoxautinh.server.service.Counter;
import com.chotoxautinh.server.service.CounterException;

@Component
public class CounterDaoImpl implements CounterDao {

	@Autowired
	private CounterRepository repository;

	@Override
	public int getNextSequence(String collectionName) throws CounterException {

		Counter counter = repository.findById(collectionName);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (counter == null) {
			throw new CounterException("Unable to get sequence id for collection : " + collectionName);
		}
		counter.setSeq(counter.getSeq() + 1);
		repository.save(counter);
		return counter.getSeq();

	}

	private Counter findByCounterId(String id) {
		return repository.findById(id);
	}

	@Override
	public boolean isKeyExist(String id) {
		Counter doc = findByCounterId(id);
		if (doc == null)
			return false;
		return true;
	}

	@Override
	public void initNewDoc(String id) {
		repository.save(new Counter(id));
	}

}
