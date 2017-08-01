package com.kaishengit;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kaishengit.pojo.Student;
import com.kaishengit.util.HibernateUtil;

public class CriteriaTest {
	
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
	
	
	
	
	@org.junit.Test
	public void findAll(){
		
			Criteria criteria=session.createCriteria(Student.class);
			criteria.add(Restrictions.and(Restrictions.eq("name", "jick"),Restrictions.eq("age", 20)));
			Student student=(Student) criteria.uniqueResult();
			
//			for(Student stu:student){
//				System.out.println(stu);
		
		
//			}
			student.setAge(21);
		
		
//			session.saveOrUpdate(student);
	
				
		
	}
	@Test
	public void findName(){
		Criteria criteria=session.createCriteria(Student.class);
		
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		List<Student> students=criteria.list();
		for(Student stu:students){
			System.out.println(stu);
		}
		
		
		
		
		
	}

}
