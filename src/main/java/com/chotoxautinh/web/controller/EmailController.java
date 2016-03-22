package com.chotoxautinh.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chotoxautinh.dao.EmailDao;
import com.chotoxautinh.model.Email;

@Controller
@RequestMapping("/email")
public class EmailController {

	private static final int PAGE_SIZE = 15;

	@Autowired
	private EmailDao emailDao;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		return "index";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<Email> listEmails(
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
		return emailDao.findEmailsByPage(new PageRequest(pageNumber - 1, PAGE_SIZE));
	}
}
