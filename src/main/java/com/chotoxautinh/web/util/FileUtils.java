/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	public static String load(File file) {
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] buffer = new char[4 * 1024];
			int read = -1;
			StringBuilder builder = new StringBuilder();
			while ((read = reader.read(buffer)) != -1) {
				builder.append(new String(buffer, 0, read));
			}
			return builder.toString();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
			return null;
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			return null;
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	public static void save(File file, String content) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
}
