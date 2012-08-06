package ru.hospital.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Общая функциональность для преобразования и форматирования времени и дат.
 */
public final class DateUtils {

	private DateUtils() {

	}

//---------------------------------------------//
	public static String getFullDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		try {
			return formatter.format(date);
		} catch (Exception e){
			return null;
		}
	}
//---------------------------------------------//
	public static Date getFullDate(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		try {
			return formatter.parse(date);
		} catch (Exception e){
			return null;
		}
	}
//---------------------------------------------//

	public static Date getNextDay(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour > 7){
			day++;
		}
		calendar.set(Calendar.DAY_OF_YEAR, day);
		calendar.set(Calendar.HOUR_OF_DAY, 7);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 1);

		return calendar.getTime();
	}
}

