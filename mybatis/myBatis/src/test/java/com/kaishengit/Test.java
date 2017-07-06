package com.kaishengit;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		 sqlSession=SqlSessionManager.getSqlSession(true);
		 studentMapper=sqlSession.getMapper(StudentMapper.class);
	}
	
	
	
//	@org.junit.Test
//	public void find(){
//		
//		try {
//			Reader reader=Resources.getResourceAsReader("mybatis.xml");
//			SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
//			SqlSessionFactory sessionFactory=sqlSessionFactoryBuilder.build(reader);
//			SqlSession sqlSession=sessionFactory.openSession();
//			
//		
//			StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
//			
////			Student stu =sqlSession.selectOne("com.kaishengit.mapper.StudentMapper.findById", 1);
//			Student stu=studentMapper.findById(1);
//			
//			System.out.println(stu.getName());
//			
//			sqlSession.close();
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//		
//
//
//
//
//	}

	@org.junit.Test
	public void findAll(){
//		SqlSession sqlSession=SqlSessionManager.getSqlSession(false);
//		StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
		
		List<Student> list=studentMapper.findClass();
		for(Student stu:list){
			System.out.println(stu);
			
		}
		
	}

	@org.junit.Test
	public void findclass(){}
	
	
	@After
	public void after(){
		sqlSession.close();
	}
	@org.junit.Test
	public void find(){
		
		Student stu=studentMapper.selectStudent(1);
		System.out.println(stu);
		
		
//		List<Student> list=sqlSession.selectList("com.kaishengit.mapper.StudentMapper.find");
//		for(Student stu:list){
//			System.out.println(stu);
//		}
	}
	@org.junit.Test
	public void save(){
//		Student stu=new Student();
//		stu.setName("tom");
//		stu.setPassword("1213");
//		stu.setClassId(1);
//		studentMapper.save(stu);
//		System.out.println(stu.getId());
		
		List<Student> list=sqlSession.selectList("com.kaishengit.mapper.StudentMapper.findName");
		for(Student stu:list){
			
			
			System.out.println(stu.getName());
		}
	}
	@org.junit.Test
	public void findByClassid(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("name","jick");
		map.put("password","123");
		Student stu=studentMapper.findByNameAndClassId("jick", "123");
//		Student stu =sqlSession.selectOne("com.kaishengit.mapper.StudentMapper.findByNameAndClassId",map);
//		Student stu =sqlSession.selectOne("com.kaishengit.mapper.StudentMapper.findById",1);
		System.out.print(stu);
	}
	@org.junit.Test
	public void saveList(){
		List<Student> list=new ArrayList<Student>();
		Student stu=new Student();
		stu.setName("das");
		stu.setPassword("123");
		stu.setClassId(2);
		Student stu1=new Student();
		stu1.setName("das");
		stu1.setPassword("123");
		stu1.setClassId(2);
		Student stu2=new Student();
		stu2.setName("das");
		stu2.setPassword("123");
		stu2.setClassId(2);
		list.add(stu);
		list.add(stu1);
		list.add(stu2);
		studentMapper.saveList(list);


	}


}

