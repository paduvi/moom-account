package com.chotoxautinh.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chotoxautinh.server.dao.FaceAccountDao;
import com.chotoxautinh.server.model.FaceAccount;

@Controller
@RequestMapping("/face")
public class TestController {

	Logger logger = LoggerFactory.getLogger(TestController.class);

	private int PAGE_SIZE = 15;

	@Autowired
	private FaceAccountDao faceAccountDao;

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.page");
		return mv;
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addUser(@RequestBody FaceAccount account) {
		faceAccountDao.addFaceAccount(account);
		return true;
	}

	@RequestMapping(value = "/update-account", method = RequestMethod.POST)
	public @ResponseBody Boolean updateUser(@RequestBody FaceAccount account) {
		faceAccountDao.updateFaceAccount(account);
		return true;
	}

	@RequestMapping(value = "/del-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delUser(@RequestBody FaceAccount account) {
		faceAccountDao.removeFaceAccount(account);;
		return true;
	}

}
