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
import com.chotoxautinh.server.service.GroupFilter;

@Controller
@RequestMapping("group")
public class GroupController {

	private static final int PAGE_SIZE = 15;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private FaceAccountDao faceAccountDao;

	@Autowired
	private GroupFilter groupFilter;

	@RequestMapping(value = "/list-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Group> listGroup(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		return groupDao.findGroupsByPage(groupFilter.build(id, name),
				new PageRequest(pageNumber - 1, PAGE_SIZE, Direction.ASC, "name")).getContent();
	}

	@RequestMapping(value = "/add-to-group", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addToGroup(@RequestBody FaceAccount faccount,
			@RequestParam(value = "group", required = false) String groupId) {
		return faceAccountDao.addFaceAccountToGroup(faccount, groupId);
	}
	
	@RequestMapping(value = "/remove-face", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean removeFaceAccount(@RequestBody FaceAccount faccount,
			@RequestParam(value = "group", required = false) String groupId) {
		return faceAccountDao.removeFaceAccount(faccount);
	}

	@RequestMapping(value = "/create-group", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean createGroup(@RequestBody Group group) {
		groupDao.addGroup(group);
		return true;
	}

	@RequestMapping(value = "/load", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Group loadGroup(@RequestParam(value = "id", required = true) String id) throws ParseException {
		return groupDao.findGroupById(id);
	}
}
