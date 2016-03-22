package com.chotoxautinh.web.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chotoxautinh.dao.CounterDao;

public class CustomContextLoaderListener {

	private static Log log = LogFactory.getLog(CustomContextLoaderListener.class);

	@Autowired
	private CounterDao counterDao;

	public void initCounterCollections() {
		insertCounterDocIfNotExist("emails");
	}

	private void insertCounterDocIfNotExist(String key) {
		if (counterDao.isKeyExist(key))
			return;
		counterDao.createNewDoc(key);
		log.info("\n Created new counter document with _id: " + key + " \n");
	}
}
