/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
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

import com.chotoxautinh.server.dao.EmailDao;
import com.chotoxautinh.server.dao.FaceAccountDao;
import com.chotoxautinh.server.model.Email;
import com.chotoxautinh.server.model.FaceAccount;
import com.chotoxautinh.server.service.FaceAccountFilter;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/faccount")
public class FaceAccountController {

	Logger logger = LoggerFactory.getLogger(FaceAccountController.class);

	private int PAGE_SIZE = 15;

	@Autowired
	private FaceAccountDao faceAccountDao;

	@Autowired
	private EmailDao emailDao;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.face");
		return mv;
	}

	@RequestMapping(value = "/list-face", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FaceAccount> listAccount(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "number", required = false) String phone,
			@RequestParam(value = "g", required = false) int group,
			@RequestParam(value = "group", required = false) String groupName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		Predicate predicate = new FaceAccountFilter().build(id, email, password, phone, group, groupName).getPredicate();
		return faceAccountDao.findFaceAccountsByPage(predicate, new PageRequest(pageNumber - 1, PAGE_SIZE, Direction.ASC, "email")).getContent();
	}

	@RequestMapping(value = "/face-count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long countAccount(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "number", required = false) String phone,
			@RequestParam(value = "g", required = false) int group,
			@RequestParam(value = "group", required = false) String groupName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		Predicate predicate = new FaceAccountFilter().build(id, email, password, phone, group, groupName).getPredicate();
		return faceAccountDao.count(predicate);
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addAccount(@RequestBody FaceAccount account) {
		faceAccountDao.addFaceAccount(account);
		return true;
	}

	@RequestMapping(value = "/update-account", method = RequestMethod.POST)
	public @ResponseBody Boolean updateAccount(@RequestBody FaceAccount account) {
		faceAccountDao.updateFaceAccount(account);
		return true;
	}

	@RequestMapping(value = "/del-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delAccount(@RequestBody FaceAccount account) {
		faceAccountDao.removeFaceAccount(account);
		return true;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Boolean test() {
		for (int i = 0; i < 20; i++) {
			Email email = new Email("abc" + i, "" + i + i + i);
			emailDao.addEmail(email);
		}
		return true;
	}

}
