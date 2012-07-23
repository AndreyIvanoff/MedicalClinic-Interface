package ru.hospital.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Содержит утилиты для работы со строками. Прежде чем добавлять сюда ещё
 * утилиты проверьте, что их нет в commons lang.
 */
public final class StringUtils {

//------------------------------------------//
	private StringUtils() {

	}
//------------------------------------------//
	/**
	 * Создаем набор строк по приципу <code>s = s.substring(0,n)</code>,
	 * где <code>n = [1..s.length]</code>.
	 *
	 * @param s		строка для разбивки
	 * @return		список подстрок в нижнем регистре
	 */
	public static List<String> toSearchArrays(String s) {

		if (s == null || s.isEmpty()){
			return null;
		}

		List<String> strings = new ArrayList<String>();
		for (int i = 1; i <= s.length(); i++) {
			strings.add(s.substring(0, i).toLowerCase());
		}
		return strings;
	}
//------------------------------------------//
}
