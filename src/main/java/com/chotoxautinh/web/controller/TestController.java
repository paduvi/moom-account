package com.chotoxautinh.web.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

	private int PAGE_SIZE = 10;

	@Autowired
	private EmailDao emailDao;

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.home");
		return mv;
	}

	@RequestMapping(value = "/list-test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<Email> listEmail(
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
		return emailDao.findEmailsByPage(new PageRequest(pageNumber - 1, PAGE_SIZE));
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createAccount(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "email", required = true) String email) {
		System.out.println("user: " + username + " password: " + password + " email: " + email);
		//emailDao.updateEmail(new Email(username, password, email));
		return "ok";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<Email> listEmails(
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
