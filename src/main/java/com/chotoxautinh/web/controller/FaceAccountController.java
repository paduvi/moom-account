/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.controller;

import java.text.ParseException;
import java.util.LinkedList;
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
import com.chotoxautinh.server.model.QFaceAccount;
import com.chotoxautinh.server.service.FaceAccountFilter;
import com.mysema.query.types.OrderSpecifier;

@Controller
@RequestMapping("/user/faccount")
public class FaceAccountController {

	Logger logger = LoggerFactory.getLogger(FaceAccountController.class);

	private int PAGE_SIZE = 15;

	@Autowired
	private FaceAccountDao faceAccountDao;

	@Autowired
	private EmailDao emailDao;

	@Autowired
	private FaceAccountFilter faceAccountFilter;

	@RequestMapping("")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("web.face");
		return mv;
	}

	@RequestMapping(value = "/list-face-by-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FaceAccount> listAccount(
			@RequestParam(value = "gName", required = false) String groupName,
			@RequestParam(value = "gId", required = false) Integer groupId) throws ParseException {
		if ((groupName == null || groupName.isEmpty()) && (groupId == null)) {
			groupId = FaceAccountFilter.LATEST;
		}
		OrderSpecifier<Integer> sortSpec = QFaceAccount.faceAccount.group.castToNum(Integer.class).asc();
		return faceAccountDao.findAllFaceAccounts(faceAccountFilter.build(null, null, null, null, groupId, groupName),
				sortSpec);
	}

	@RequestMapping(value = "/list-face-have-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FaceAccount> listAccountHaveGroup(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "gName", required = false) String groupName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		OrderSpecifier<Integer> groupSpec = QFaceAccount.faceAccount.group.castToNum(Integer.class).asc();
		OrderSpecifier<String> emailSpec = QFaceAccount.faceAccount.email.asc();
		List<FaceAccount> accounts = faceAccountDao.findAllFaceAccounts(
				faceAccountFilter.build(id, email, password, phone, FaceAccountFilter.HAVE, groupName), groupSpec,
				emailSpec);
		List<String> listGroup = new LinkedList<>();
		List<FaceAccount> temp = new LinkedList<>();
		int index = 0;
		for (FaceAccount faceAccount : accounts) {
			if (!listGroup.contains(faceAccount.getGroup())) {
				listGroup.add(faceAccount.getGroup());
				index++;
			}
			if (index > (pageNumber - 1) * PAGE_SIZE && index <= pageNumber * PAGE_SIZE)
				temp.add(faceAccount);
		}
		return temp;
	}

	@RequestMapping(value = "/face-count-have-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long countAccountHaveGroup(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "gName", required = false) String groupName) throws ParseException {
		List<FaceAccount> accounts = faceAccountDao.findAllFaceAccounts(
				faceAccountFilter.build(id, email, password, phone, FaceAccountFilter.HAVE, groupName));
		List<String> listGroup = new LinkedList<>();
		long index = 0;
		for (FaceAccount faceAccount : accounts) {
			if (!listGroup.contains(faceAccount.getGroup())) {
				listGroup.add(faceAccount.getGroup());
				index++;
			}
		}
		return index;
	}

	@RequestMapping(value = "/list-face-none-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FaceAccount> listAccountNoneGroup(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) throws ParseException {
		return faceAccountDao.findFaceAccountsByPage(
				faceAccountFilter.build(id, email, password, phone, FaceAccountFilter.NONE, null),
				new PageRequest(pageNumber - 1, PAGE_SIZE, Direction.ASC, "email")).getContent();
	}

	@RequestMapping(value = "/face-count-none-group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long countAccountNoneGroup(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "phone", required = false) String phone) throws ParseException {
		return faceAccountDao.count(faceAccountFilter.build(id, email, password, phone, FaceAccountFilter.NONE, null));
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
