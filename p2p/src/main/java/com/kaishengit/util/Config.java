package com.kaishengit.util;

import java.io.IOException;
import java.util.Properties;


public class Config {

	private static Properties pro = new Properties();
	
	static {
		try {
			pro.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 根据key值查询对应的value
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return  pro.getProperty(key);
	}
}
