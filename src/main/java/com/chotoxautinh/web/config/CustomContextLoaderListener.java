package com.chotoxautinh.web.config;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;

import com.chotoxautinh.dao.CounterDao;

public class CustomContextLoaderListener extends ContextLoaderListener {

	private static Log log = LogFactory.getLog(CustomContextLoaderListener.class);

	@Autowired
	private CounterDao counterDao;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("\n Spring MVC Application Init \n");
		createCounterCollections();
	}

	void createCounterCollections() {
		insertCounterDocIfNotExist("emails");
	}

	void insertCounterDocIfNotExist(String key) {
		if (!counterDao.isKeyExist(key)) {
			counterDao.createNewDoc(key);
			log.info("Created new counter document with _id: " + key);
		}
	}
}
