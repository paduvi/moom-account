package com.chotoxautinh.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chotoxautinh.model.FaceAccount;

@Controller
@RequestMapping("/")
public class HomeController {
	
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
}
