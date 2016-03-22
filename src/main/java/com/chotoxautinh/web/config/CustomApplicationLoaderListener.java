package com.chotoxautinh.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.chotoxautinh.dao.CounterDao;

public class CustomApplicationLoaderListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private CounterDao counterDao;

	private void initCounterCollections() {
		insertCounterDocIfNotExist("emails");
	}

	private void insertCounterDocIfNotExist(String key) {
		if (counterDao.isKeyExist(key))
			return;
		counterDao.createNewDoc(key);
		System.out.println("Created new counter document with _id: " + key + " \n");
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("\n Spring MVC Application Inited\n");
		initCounterCollections();
	}
}
