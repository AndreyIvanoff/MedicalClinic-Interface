package ru.hospital.utils;

import java.text.SimpleDateFormat;
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
}

