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
import com.chotoxautinh.server.model.Email;
import com.chotoxautinh.server.service.EmailFilter;

@Controller
@RequestMapping("/user/email")
public class EmailController {

	Logger logger = LoggerFactory.getLogger(EmailController.class);

	private int PAGE_SIZE = 15;

	@Autowired
	private EmailDao emailDao;

	@RequestMapping("")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.email");
		return mv;
	}

	@RequestMapping(value = "/list-email", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Email> listEmail(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "number", required = false) String phone,
			@RequestParam(value = "birthday", required = false) String birthday,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		return emailDao.findEmailsByPage(EmailFilter.build(id, username, password, email, phone, birthday),
				new PageRequest(pageNumber - 1, PAGE_SIZE, Direction.ASC, "username")).getContent();
	}

	@RequestMapping(value = "/email-count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long countEmail(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "birthday", required = false) String birthday,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		return emailDao.count(EmailFilter.build(id, username, password, email, phone, birthday));
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addEmail(@RequestBody Email email) {
		return emailDao.addEmail(email);
	}

	@RequestMapping(value = "/update-account", method = RequestMethod.POST)
	public @ResponseBody Boolean updateEmail(@RequestBody Email email) {
		return emailDao.updateEmail(email);
	}

	@RequestMapping(value = "/del-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delEmail(@RequestBody Email email) {
		return emailDao.removeEmail(email);
	}

}
