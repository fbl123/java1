package com.kaishengit.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.kaishengit.exception.DataAccessException;

public class ConnectionManager {

	private static String DRIVER;//= "com.mysql.jdbc.Driver";
	private static String URL;// = "jdbc:mysql:///invest_db";
	private static String USERNAME;// = "root";
	private static String PASSWORD ;//= "rootroot";
	private static BasicDataSource datasource = new BasicDataSource();
	
	static{
		
		DRIVER = Config.get("jdbc.driver");
		URL = Config.get("jdbc.url");
		USERNAME = Config.get("jdbc.username");
		PASSWORD = Config.get("jdbc.password");
		
		//设置属性
		datasource.setDriverClassName(DRIVER);
		datasource.setUrl(URL);
		datasource.setUsername(USERNAME);
		datasource.setPassword(PASSWORD);
		
		datasource.setInitialSize(5);
		datasource.setMaxIdle(20);
		datasource.setMinIdle(5);
		datasource.setMaxWaitMillis(5000);
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			throw new DataAccessException("获取数据库连接异常",e);
		}
		return conn;
	}
	
	public static BasicDataSource getDataSource(){
		return datasource;
	}
	
	
	/*public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}*/
	
}
