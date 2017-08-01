package com.kaishengit;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kaishengit.pojo.Student;
import com.kaishengit.util.HibernateUtil;

public class SQLTest {
	private Session session;
	
	
	
	@Before
	public void before(){
		
		session=HibernateUtil.getSession();
		session.beginTransaction();
		
	}
	@After
	public void after(){
		session.getTransaction().commit();
		
	}
	
	@Test
	public void find(){
		String sql="select * from student";
		SQLQuery sqlQuery=session.createSQLQuery(sql).addEntity(Student.class);
		
		
		
		List<Student> students=sqlQuery.list();
		
		System.out.println(students);
		
		
		
		
		
		
	}
	

}
