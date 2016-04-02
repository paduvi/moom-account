/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chotoxautinh.server.dao.FaceAccountDao;
import com.chotoxautinh.server.dao.GroupDao;
import com.chotoxautinh.server.model.FaceAccount;
import com.chotoxautinh.server.model.Group;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private FaceAccountDao faceAccountDao;

	@RequestMapping(value = "/list-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Group> listAccount() throws ParseException {
		return groupDao.findAllGroups();
	}

	@RequestMapping(value = "/add-to-group", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addToGroup(@RequestBody FaceAccount faccount,
			@RequestParam(value = "group", required = false) String groupId) {
		faceAccountDao.addFaceAccountToGroup(faccount, groupId);
		return true;
	}
}
