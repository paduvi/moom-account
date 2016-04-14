/**
 * @author chotoxautinh
 *
 * Apr 2, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	private static final String DATE_FORMAT = "dd-MM-yyyy";

	public static boolean isValidFormat(String value) throws ParseException {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		date = sdf.parse(value);
		if (!value.equals(sdf.format(date))) {
			date = null;
		}
		return date != null;
	}
}
