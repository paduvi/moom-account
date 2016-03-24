package com.chotoxautinh.web.controller;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chotoxautinh.server.dao.EmailDao;
import com.chotoxautinh.server.model.Email;

@Controller
@RequestMapping("/")
public class TestController {

	Logger logger = LoggerFactory.getLogger(TestController.class);
	
	private int PAGE_SIZE = 10;

	@Autowired
	private EmailDao emailDao;

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.home");
		return mv;
	}

	@RequestMapping(value = "/list-test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Email> listEmail(
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
		return emailDao.findEmailsByPage(new PageRequest(pageNumber - 1, PAGE_SIZE)).getContent();
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addUser(@RequestBody Email email) {
		emailDao.addEmail(email);
		return true;
	}
	
	@RequestMapping(value = "/update-account", method = RequestMethod.POST)
	public @ResponseBody Boolean updateUser(@RequestBody Email email) {
		logger.info(email.getUsername());
		emailDao.updateEmail(email);
		return true;
	}
	
	@RequestMapping(value = "/del-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delUser(@RequestBody Email email) {
		logger.info(email.getUsername());
		emailDao.removeEmail(email);
		return true;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Email> listEmails(
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
		List<Email> list = new LinkedList<>();
		for (int i = 0; i < 13; i++) {
			list.add(emailDao.addEmail(new Email("abc" + i, "123" + i, "abc" + i + "@gmail.com")));
		}
		return list;
	}

	@RequestMapping(value = "/test-auth", method = RequestMethod.GET)
	public String testAuth() {
		return "index";
	}
}
