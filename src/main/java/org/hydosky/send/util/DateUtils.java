package org.hydosky.send.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 */
public class DateUtils extends PropertyEditorSupport {

	/**
	 * HH
	 */
	public static final SimpleDateFormat hour_time_sdf = new SimpleDateFormat("HH:ss");

	/**
	 * 判断当前时间是否在00:00-00:00之间
	 */
	public static boolean isHourBetween(String start, String end) {
		Date now = null;
		Date beginTime = null;
		Date endTime = null;
		try {
			now = hour_time_sdf.parse(hour_time_sdf.format(new Date()));
			beginTime = hour_time_sdf.parse(start);
			endTime = hour_time_sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar date = Calendar.getInstance();
		date.setTime(now);
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(beginTime);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(endTime);
		return date.after(beginDate) && date.before(endDate);
	}

	/**
	 * 获取两个时间相差的总毫秒数
	 */
	public static long millsBetween(String start, String end) {
		Date beginTime = null;
		Date endTime = null;
		try {
			beginTime = hour_time_sdf.parse(start);
			endTime = hour_time_sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(beginTime);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(endTime);
		return endDate.getTimeInMillis() - beginDate.getTimeInMillis();
	}

}
