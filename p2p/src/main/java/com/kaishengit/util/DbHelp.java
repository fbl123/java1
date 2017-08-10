package com.kaishengit.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.exception.DataAccessException;

public class DbHelp {
	
	private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	
	/**获取connection连接
	 * @return
	 */
	public static Connection getConnection() {
		return ConnectionManager.getConnection();
	}
	
	/**
	 * update() 执行insert update delete
	 * @param sql
	 * @param params
	 */
	public static void update(String sql,Object... params){
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		try {
			runner.update(sql, params);
			logger.debug("SQL:{},params:{}",sql,params);
		} catch (SQLException e) {
			throw new DataAccessException("执行" + sql +"异常",e);
		} 
		
	}
	
	public static <T> T query(String sql,ResultSetHandler<T> rsh, Object... params) 
			throws DataAccessException{
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		T t = null;
		try {
			t = runner.query(sql, rsh, params);
			logger.debug("SQL:{},params:{}",sql,params);
		} catch (SQLException e) {
			throw new DataAccessException("执行" + sql +"异常",e);
		} 
		return t;
	}

	public static <T> T insert(String sql,ResultSetHandler<T> rsh ,Object... params) {
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		T t = null;
		try {
			t = runner.insert(sql, rsh ,params);
			logger.debug("SQL:{},params:{}",sql,params);
		} catch (SQLException e) {
			throw new DataAccessException("执行" + sql +"异常",e);
		} 
		return t;
	}
	
	
}
