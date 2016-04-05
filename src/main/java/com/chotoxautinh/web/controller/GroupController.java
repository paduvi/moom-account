/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.controller;

import java.text.ParseException;
import java.util.List;

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

import com.chotoxautinh.server.dao.FaceAccountDao;
import com.chotoxautinh.server.dao.GroupDao;
import com.chotoxautinh.server.model.FaceAccount;
import com.chotoxautinh.server.model.Group;
import com.chotoxautinh.server.service.FaceAccountFilter;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/group")
public class GroupController {

	private int PAGE_SIZE = 15;
	
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
		return faceAccountDao.addFaceAccountToGroup(faccount, groupId);
	}
	
	@RequestMapping(value = "/list-face", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FaceAccount> listAccount(@RequestParam(value = "group", required = false) String group,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		Predicate predicate = new FaceAccountFilter(group).getPredicate();
		return faceAccountDao.findFaceAccountsByPage(predicate, new PageRequest(pageNumber - 1, PAGE_SIZE, Direction.ASC, "username")).getContent();
	}

	@RequestMapping(value = "/face-count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long countAccount(@RequestParam(value = "group", required = false) String group) throws ParseException {
		Predicate predicate = new FaceAccountFilter(group).getPredicate();
		return faceAccountDao.count(predicate);
	}
}
