/**
 * @author chotoxautinh
 *
 * Apr 15, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class HTTPErrorHandler {
	@RequestMapping(value = "/400")
	public String error400() {
		return "error-400";
	}

	@RequestMapping(value = "/404")
	public String error404() {
		return "error-404";
	}

	@RequestMapping(value = "/500")
	public String error500() {
		return "error-500";
	}

	@RequestMapping(value = "/403")
	public String error403() {
		return "error-403";
	}
}
