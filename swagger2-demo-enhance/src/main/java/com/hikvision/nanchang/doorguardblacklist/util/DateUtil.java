package com.hikvision.nanchang.doorguardblacklist.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 时间工具类   
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public class DateUtil {

	private DateUtil() {
	}

	private static ThreadLocal<SimpleDateFormat> inputSdf = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		};
	};

	private static ThreadLocal<SimpleDateFormat> outputSdf = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		};
	};

	public static Date parse(String dateTimeString) throws ParseException {
		return inputSdf.get().parse(dateTimeString);
	}

	public static String format(Date date) {
		return outputSdf.get().format(date);
	}

	/**
	* @Description: 获取当前时间到第二天凌晨的秒数
	* @param @return
	* @return Date 
	* @throws
	 */
	public static Long tomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis() - System.currentTimeMillis();
	}
}
