package com.fyft.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

	public static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String easyFormat = "yyyy年MM月dd日 HH:mm";
	public static String formatDate(Date d,String format){
		Locale locale = Locale.CHINA;
		SimpleDateFormat sdf = new SimpleDateFormat(format,locale);
		return sdf.format(d);
	}
	
	public static String getCurrentDate(){
		return getCurrentDate(defaultFormat);
	}
	public static String getCurrentDate(String format){
		return formatDate(new Date(),format);
	}
	public static Date getDate(String str,String format){
		Locale locale = Locale.CHINA;
		SimpleDateFormat sdf = new SimpleDateFormat(format,locale);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return new Date();
		}
	}
	public static Date getDate(String str){
		return getDate(str,"yyyy-MM-dd");
	}
	/**
	 * 
	 * @param d
	 * @return
	 */
	public static String getLunarDate(Date d){
		LunarCalendar lc = new LunarCalendar(d);
		return lc.toString();
	}
	public static String getLunarDate(){
		return getLunarDate(new Date());
	}
	public static String formatDate(String str,String informat,String outFormat){
		return formatDate(getDate(str, informat),outFormat);
	}
}
