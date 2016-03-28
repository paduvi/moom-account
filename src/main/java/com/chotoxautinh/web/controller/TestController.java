package com.chotoxautinh.web.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chotoxautinh.server.dao.FaceAccountDao;
import com.chotoxautinh.server.model.Email;
import com.chotoxautinh.server.model.FaceAccount;
import com.chotoxautinh.server.service.EmailFilter;
import com.chotoxautinh.server.service.FaceAccountFilter;
import com.mysema.query.types.Predicate;

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
	
	@RequestMapping(value = "/list-face", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FaceAccount> listAccount(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "number", required = false) String phone,
			@RequestParam(value = "group", required = false) String group,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		Predicate predicate = new FaceAccountFilter(id, password, email, phone, group).getPredicate();
		return faceAccountDao.findFaceAccountsByPage(predicate, new PageRequest(pageNumber - 1, PAGE_SIZE, Direction.ASC, "username")).getContent();
	}

	@RequestMapping(value = "/face-count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long countAccount(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "number", required = false) String phone,
			@RequestParam(value = "group", required = false) String group,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		Predicate predicate = new FaceAccountFilter(id, password, email, phone, group).getPredicate();
		return faceAccountDao.count(predicate);
	}

	@RequestMapping(value = "/list-face", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FaceAccount> listEmail(@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		return faceAccountDao.findAllFaceAccounts();
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
