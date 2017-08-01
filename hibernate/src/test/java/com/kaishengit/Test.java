package com.kaishengit;

import com.kaishengit.pojo.Student;
import com.kaishengit.util.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;

public class Test {
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
    public void  save(){
        Configuration configuration=new Configuration().configure();
//        SessionFactory sessionFactory=configuration.buildSessionFactory();
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry);
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student=new Student();
        student.setAge(18);
        student.setName("jick");
        session.save(student);

        session.getTransaction().commit();

    }
    @org.junit.Test
    public void update(){
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        
//        Student stu=(Student) session.get(Student.class,1);
        Student stu=(Student) session.load(Student.class, 1);
        stu.setName("tom");
        
        
        
        session.getTransaction().commit();
        System.out.println(stu.getName());

    }
    @org.junit.Test
    public void findByName(){
    	Session session=HibernateUtil.getSession();
    	session.beginTransaction();
    	
    	String hql="from Student where name=?";
    	Query query=session.createQuery(hql);

    	query.setParameter(0, "jick");
    	Student stu=(Student) query.uniqueResult();
    	
    	if(stu!=null){
    		System.out.println(stu.getName());
    	}
    	
    	
    	
    	
    	
    	session.getTransaction().commit();
    	
    }

    @org.junit.Test
    public void findName(){
    	SQLQuery sql=session.createSQLQuery("select* from student where name=?");
    	sql.setParameter(0, "tom");
    	sql.addEntity(Student.class);
    	Student stu=(Student) sql.uniqueResult();
    	System.out.println(stu);
    	
    	
    	
//    	String hql="select name from Student";
//    	Query query=session.createQuery(hql);
//    	Object obj=query.uniqueResult();
//    	System.out.println(obj);
    	
    	
    }

    
    @org.junit.Test
    public void findAll(){
    	Session session=HibernateUtil.getSession();
    	session.beginTransaction();

    	String hql="from Student";
    	Query query=session.createQuery(hql);

    	List<Student> stuList=query.list();
    	for(Student stu:stuList){
    		System.out.println(stu.getName());
    	}

    	session.getTransaction().commit();
    	
    	
    }


    @org.junit.Test
    public void findById(){
       Session session= HibernateUtil.getSession();
       session.beginTransaction();
       Student student= (Student) session.get(Student.class,1);

        System.out.println(student.getAge());
       session.getTransaction().commit();
       

    }
    
    
}
