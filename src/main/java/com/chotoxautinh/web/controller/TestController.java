package com.chotoxautinh.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chotoxautinh.server.dao.EmailDao;
import com.chotoxautinh.server.model.Email;

@Controller
@RequestMapping("/face")
public class TestController {

	Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private EmailDao emailDao;


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Boolean test() {
		for (int i = 0; i < 20; i++) {
			Email email = new Email("abc" + i, "" + i + i + i);
			emailDao.addEmail(email);
		}
		return true;
	}

}
