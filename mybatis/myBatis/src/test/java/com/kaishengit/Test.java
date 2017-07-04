package com.kaishengit;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kaishengit.entity.Student;

public class Test {
	
	@org.junit.Test
	public void find(){
		
		try {
			Reader reader=Resources.getResourceAsReader("mybatis.xml");
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
			SqlSessionFactory sessionFactory=sqlSessionFactoryBuilder.build(reader);
			SqlSession sqlSession=sessionFactory.openSession();
			
			Student stu =sqlSession.selectOne("com.kaishengit.mapper.StudentMapper.findById", 1);
			System.out.println(stu.getName());
			
			sqlSession.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
}
