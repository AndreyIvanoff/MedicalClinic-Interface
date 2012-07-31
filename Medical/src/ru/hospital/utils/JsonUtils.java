package ru.hospital.utils;

import com.google.gson.Gson;

public class JsonUtils {

//-------------------------------------------//	
	public static String toJSON(Object object){
		Gson gson = new Gson();
		return gson.toJson(object);
	}	
//-------------------------------------------//
	public static <T> T fromJSON(Class<T> type, String json) throws Exception{
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}	
//-------------------------------------------//	
	
}
