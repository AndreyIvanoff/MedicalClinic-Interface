package ru.hospital.utils;

import ru.hospital.model.Administrator;

/*
 * Класс для численных переменных или для переменных которые могут
 * меняться в процессе работы сервера
 */
public class Const {

	public static Integer CACHE_ENTITY = 3600*24;

	public static Long PERMISSION_LEVEL_TO_CREATE_DOCTORS = Administrator.PERMISSION_LEVEL_HIGH;
	public static Long PERMISSION_LEVEL_TO_CREATE_SERVICES = Administrator.PERMISSION_LEVEL_HIGH;
	public static Long PERMISSION_LEVEL_TO_CREATE_PACIENTS = Administrator.PERMISSION_LEVEL_MEDIUM;


}
