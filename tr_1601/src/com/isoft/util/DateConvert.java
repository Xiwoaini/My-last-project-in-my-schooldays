package com.isoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author f
 * @Description 日期转换
 * */
public class DateConvert {

	private static Date date=new Date();
	/**
	 * @param check日期  check周期
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * */
	public static String convertDate(String check_date,String check_circle) throws NumberFormatException, ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	 
		long l=(sdf.parse(check_date).getTime()+
 			(Long.parseLong(check_circle)*86400000))-date.getTime();
		String s=String.valueOf(Math.round((l/1000)/86400));
		return s;
	}
	
	
}
