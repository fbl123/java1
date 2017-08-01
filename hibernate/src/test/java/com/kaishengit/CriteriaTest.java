package com.kaishengit;

import java.util.List;
import java.util.Set;

import com.kaishengit.pojo.Aconter;
import com.kaishengit.pojo.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kaishengit.pojo.Student;
import com.kaishengit.util.HibernateUtil;

import javax.jws.soap.SOAPBinding;

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


	//		OneToMany
	@Test
	public void save(){
		Book book =new Book();
		Aconter aconter=new Aconter();
		aconter.setName("江南");
		book.setName("缥缈录1");
		book.setAconter(aconter);

		session.save(aconter);
		session.save(book);




	}
	@Test
	public void find(){
		Book book= (Book) session.get(Book.class,1);


		for(Book book1:book.getAconter().getBooks()){
			System.out.println(book1.getName());
		}

//		System.out.println(book.getName());

//		System.out.println(book.getAconter().getName());



//		Aconter aconter= (Aconter) session.get(Aconter.class,3);
//		Set<Book> books=aconter.getBooks();
//		for(Book book:books){
//			System.out.println(book);
//		}





	}


	//ManyToMany
	


}
