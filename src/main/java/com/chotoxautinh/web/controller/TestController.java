package com.chotoxautinh.web.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chotoxautinh.server.dao.EmailDao;
import com.chotoxautinh.server.model.Email;
import com.chotoxautinh.server.model.FaceAccount;

@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private EmailDao emailDao;

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.home");
		return mv;
	}

	@RequestMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FaceAccount getAccount() {
		FaceAccount faceAccount = new FaceAccount();
		faceAccount.setEmail("abc@gmail.com");
		faceAccount.setPassword("123456");
		return faceAccount;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<Email> listEmails(
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
		List<Email> list = new LinkedList<>();
		for (int i = 0; i < 13; i++) {
			list.add(emailDao.addEmail(new Email("abc" + i, "123" + i)));
		}
		return list;
	}
}
