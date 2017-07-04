package com.kaishengit.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionManager {
	public static SqlSessionFactory sessionFactory=getSqlSessionFactory();

	private static SqlSessionFactory getSqlSessionFactory()  {
			try {
				Reader reader=Resources.getResourceAsReader("mybatis.xml");
				SqlSessionFactory sessionFactory= new SqlSessionFactoryBuilder().build(reader);
				return sessionFactory;
				
			} catch (IOException e) {
				throw new RuntimeException("º”‘ÿ ß∞‹");
			}
	
	}
	public static SqlSession getSqlSession(Boolean bl){
		return sessionFactory.openSession(bl);
		
	}
	
	
}
