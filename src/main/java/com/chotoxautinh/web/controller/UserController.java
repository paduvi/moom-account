/**
 * @author chotoxautinh
 *
 * Mar 24, 2016 - http://chotoxautinh.com/
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chotoxautinh.server.dao.UserDao;
import com.chotoxautinh.server.model.User;
import com.chotoxautinh.server.service.UserFilter;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	private int PAGE_SIZE = 15;

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/faccount/");
		}
		ModelAndView mv = new ModelAndView("web.user.login");
		if (error != null) {
			mv.addObject("error", "Invalid username and password!");
		}
		return mv;
	}
	@RequestMapping("")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.user");
		return mv;
	}

	@RequestMapping("/information")
	public ModelAndView info() {
		ModelAndView mv = new ModelAndView("web.user.info");
		return mv;
	}

	@RequestMapping(value = "/list-user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> listUser(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "email", required = false) String fullname,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		return userDao.findUsersByPage(UserFilter.build(id, username, password, fullname),
				new PageRequest(pageNumber - 1, PAGE_SIZE, Direction.ASC, "username")).getContent();
	}

	@RequestMapping(value = "/user-count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long countUser(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "fullname", required = false) String fullname,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		return userDao.count(UserFilter.build(id, username, password, fullname));
	}

	@RequestMapping(value = "/create-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addEmail(@RequestBody User user) {
		return userDao.createUser(user);
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public @ResponseBody Boolean updateEmail(@RequestBody User user) {
		return userDao.updateUser(user);
	}

	@RequestMapping(value = "/del-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delEmail(@RequestBody User user) {
		return userDao.removeUser(user);
	}
}
