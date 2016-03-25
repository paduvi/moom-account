/**
 * @author chotoxautinh
 *
 * Mar 24, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.util;

import java.io.File;
import java.util.List;

import com.chotoxautinh.web.model.User;

public class UserUtils {
	private final static String FOLDER_NAME = "moom";
	private final static String EXT = ".user";

	public static List<User> list() {
		return DataUtils.list(FOLDER_NAME, EXT, User.class);
	}

	public static File getFolder() {
		return DataUtils.getFolder(FOLDER_NAME);
	}

	public static File[] load() {
		return DataUtils.load(FOLDER_NAME, EXT);
	}

	public static User load(String name) {
		File file = new File(DataUtils.getFolder(FOLDER_NAME), name + EXT);
		return DataUtils.<User> load(file, User.class);
	}

	public static boolean save(User user) {
		return DataUtils.save(FOLDER_NAME, user, user.getUsername(), EXT, false);
	}

	public static boolean create(User user) {
		return DataUtils.save(FOLDER_NAME, user, user.getUsername(), EXT, true);
	}
}
