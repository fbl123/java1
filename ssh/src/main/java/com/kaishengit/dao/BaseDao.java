package com.kaishengit.dao;

import com.kaishengit.util.Condition;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
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

    public List<T> find(Condition... conditions){
       Criteria criteria=getSession().createCriteria(clazz);
       for(Condition c:conditions){
           criteria.add(get(c));
       }
       return criteria.list();
    }
   public Criterion get(Condition condition){
        switch (condition.getType()){
            case "eq":
                return Restrictions.eq(condition.getParameName(),condition.getValue());
            case "gt":
                return Restrictions.gt(condition.getParameName(),condition.getValue());
            case "lt":
                return Restrictions.lt(condition.getParameName(),condition.getValue());
            case "like":
                return Restrictions.like(condition.getParameName(),condition.getValue().toString(), MatchMode.ANYWHERE);
            case "ge":
                return Restrictions.ge(condition.getParameName(),condition.getValue());
            case "le":
                return Restrictions.le(condition.getParameName(),condition.getValue());
            default:
                break;
        }

//        if(condition.getType().equals("eq")){
//            return Restrictions.eq(condition.getParameName(),condition.getValue());
//        }else if(condition.getType().equals("ge")){
//            return Restrictions.ge(condition.getParameName(),condition.getValue());
//        }else if()
        return null;
   }

   public List<T> findByProperty(Map<String,Object> maps){
       Criteria criteria=getSession().createCriteria(clazz);
       Set<String> sets=maps.keySet();
       for(String key:sets){
           if(key.equals("page")){
               criteria.setFirstResult((Integer) maps.get(key));
           }else if(key.equals("size")){
               criteria.setMaxResults((Integer) maps.get(key));
           }
           else{
               criteria.add(Restrictions.eq(key,maps.get(key)));
           }

       }
       return criteria.list();


   }

   public T findById(PK id){
       return (T) getSession().get(clazz,id);
   }

     public Long count(Condition... conditions){
          Criteria criteria = getSession().createCriteria(clazz);

          for(Condition condition:conditions){
              criteria.add(get(condition));
          }
          criteria.setProjection(Projections.rowCount());
         return (Long) criteria.uniqueResult();
     }



}
