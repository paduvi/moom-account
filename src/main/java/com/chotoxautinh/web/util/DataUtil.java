/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataUtil {
	private final static ObjectMapper MAPPER = new ObjectMapper();

	private final static Logger LOGGER = LoggerFactory.getLogger(DataUtil.class);

	public static File getFolder(String name) {
		URL url = DataUtil.class.getResource("/");
		if (url == null)
			return null;
		try {
			File folder = new File(url.toURI());
			folder = new File(folder, name + "/");
			// LOGGER.info("folder " + folder.getAbsolutePath());
			if (!folder.exists())
				folder.mkdirs();
			return folder;
		} catch (Exception exp) {
			LOGGER.error(exp.getMessage());
			return null;
		}
	}

	public static File[] load(String name, final String ext) {
		try {
			File folder = getFolder(name);
			if (folder == null)
				return new File[0];

			File[] files = folder.listFiles(new FileFilter() {
				@Override
				public boolean accept(File p) {
					return p.isFile() && p.getName().endsWith(ext);
				}
			});

			if (files == null)
				return new File[0];
			LOGGER.info("Find users " + files.length);
			return files;
		} catch (Exception exp) {
			LOGGER.error(exp.getMessage());
			return new File[0];
		}
	}

	public static <T> List<T> list(String folder, String ext, Class<T> clazz) {
		File[] files = DataUtil.load(folder, ext);
		if (files == null || files.length < 1)
			return new ArrayList<T>(0);
		List<T> values = new LinkedList<>();
		for (File file : files) {
			T t = load(file, clazz);
			values.add(t);
		}
		return values;
	}

	public static <T> T load(File file, Class<T> clazz) {
		if (!file.exists() || file.length() < 1)
			return null;
		try {
			String json = new String(FileUtil.load(file));
			T bean = MAPPER.readValue(json, clazz);

			MethodHandles.Lookup lookup = MethodHandles.lookup();
			try {
				MethodHandle mh = lookup.findSetter(clazz, "file", File.class);
				if (mh != null)
					mh.invoke(bean, file);
			} catch (Throwable e) {
				// LOGGER.error(e);;
			}
			return bean;
		} catch (Exception exp) {
			LOGGER.error(file.getAbsolutePath());
			LOGGER.error(exp.getMessage());
			return null;
		}
	}

	public static boolean create(String folder, Object bean, String name, String ext) {
		return save(folder, bean, name, ext, true);
	}

	public static boolean save(String folder, Object bean, String name, String ext) {
		return save(folder, bean, name, ext, false);
	}

	public static boolean save(String folder, Object bean, String name, String ext, boolean create) {
		return save(DataUtil.getFolder(folder), bean, name, ext, create);
	}

	public static boolean save(File folder, Object bean, String name, String ext, boolean create) {
		try {
			File file = new File(folder, name + ext);
			if (create) {
				if (!file.exists())
					file.createNewFile();
			} else {
				if (!file.exists())
					return false;
			}
			String json = MAPPER.writeValueAsString(bean);
			FileUtil.save(file, json);
			return true;
		} catch (Exception exp) {
			LOGGER.error(exp.getMessage());
			return false;
		}

	}

	public static boolean delete(File folder, String name, String ext) {
		File oldFile = new File(folder, name + ext);
		if (oldFile.exists())
			oldFile.delete();
		return true;
	}

	public static <T> T jsonToCollections(final TypeReference<T> type, final String jsonPacket) {
		try {
			return new ObjectMapper().readValue(jsonPacket, type);
		} catch (Exception e) {
			return null;
		}
	}
}
