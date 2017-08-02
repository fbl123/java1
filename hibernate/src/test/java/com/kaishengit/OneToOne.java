package com.kaishengit;

import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.Perpor;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OneToOne {
    private Session session;
    @Before
    public void before(){
        session= HibernateUtil.getSession();
        session.beginTransaction();

    }
    @After
    public void after(){
        session.getTransaction().commit();
    }



    @Test
    public void save(){
//        Perpor perpor=new Perpor();
//        perpor.setName("jick");
//        perpor.setAge(18);
//        Address address=new Address();
//        address.setIDCard("31321321321");
//        address.setTell("321323321");
//        address.setPerpor(perpor);
//
//        session.save(perpor);
//        session.save(address);

        Perpor perpor= (Perpor) session.get(Perpor.class,2);
        System.out.println(perpor.getAddress().getTell());

        session.delete(perpor);
    }


}
