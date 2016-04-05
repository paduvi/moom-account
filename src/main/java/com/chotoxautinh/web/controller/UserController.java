/**
 * @author chotoxautinh
 *
 * Mar 24, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/user/view");
		}
		ModelAndView mv = new ModelAndView("web.user.login");
		if (error != null) {
			mv.addObject("error", "Invalid username and password!");
		}
		return mv;
	}
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.user");
		return mv;
	}
}
