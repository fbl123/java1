package com.kaishengit;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import com.kaishengit.entity.Student;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.SqlSessionManager;

public class Test {
	SqlSession sqlSession;
	StudentMapper studentMapper;
	@Before
	public void befer(){
		 sqlSession=SqlSessionManager.getSqlSession(false);
		 studentMapper=sqlSession.getMapper(StudentMapper.class);
	}
	
	
	
	@org.junit.Test
	public void find(){
		
		try {
			Reader reader=Resources.getResourceAsReader("mybatis.xml");
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
			SqlSessionFactory sessionFactory=sqlSessionFactoryBuilder.build(reader);
			SqlSession sqlSession=sessionFactory.openSession();
			
		
			StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
			
//			Student stu =sqlSession.selectOne("com.kaishengit.mapper.StudentMapper.findById", 1);
			Student stu=studentMapper.findById(1);
			
			System.out.println(stu.getName());
			
			sqlSession.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@org.junit.Test
	public void findAll(){
//		SqlSession sqlSession=SqlSessionManager.getSqlSession(false);
//		StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
		
		List<Student> list=studentMapper.findAll();
		for(Student stu:list){
			System.out.println(stu.getName());
		}
		
	}
	
	@After
	public void after(){
		sqlSession.close();
	}
}
