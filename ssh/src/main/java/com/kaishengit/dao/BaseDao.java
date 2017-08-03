package com.kaishengit.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseDao<T,PK extends Serializable> {


    Class clazz;
    public BaseDao(){
        ParameterizedType parameterizedType= (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types=parameterizedType.getActualTypeArguments();
        clazz= (Class) types[0];


    }
    @Autowired
    private SessionFactory sessionFactory;



   protected Session getSession(){
       return sessionFactory.getCurrentSession();
   }

   public List<T> findAll(){
       Criteria criteria=getSession().createCriteria(clazz);
               return criteria.list();
   }

   public void save(T t){
       getSession().saveOrUpdate(t);
   }

   public void delete(T t){
       getSession().delete(t);
   }

    /**
     *
     * @param maps
     * @return
     */
   public List<T> findByProperty(Map<String,Object> maps){
       Criteria criteria=getSession().createCriteria(clazz);
       Set<String> sets=maps.keySet();
       for(String key:sets){
           criteria.add(Restrictions.eq(key,maps.get(key)));
       }
       return criteria.list();


   }




}
