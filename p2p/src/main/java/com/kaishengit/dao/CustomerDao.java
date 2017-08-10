package com.kaishengit.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Customer;
import com.kaishengit.util.DbHelp;

public class CustomerDao {

	public List<Customer> findByParam(Map<String, Object> param) {
		String sql = "select * from t_customer where (DAYOFYEAR(birthday) - DAYOFYEAR(NOW()) BETWEEN 0 AND ?)"+
				"or (DAYOFYEAR(birthday) + DAYOFYEAR(CONCAT(YEAR(NOW()),'-12-31')) - DAYOFYEAR(NOW()) < ?) order by birthday limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class), param.get("days"),param.get("days"),param.get("start"),param.get("pageSize"));
		
	}

	public int count(Map<String, Object> param) {
		String sql = "select count(*) from t_customer where (DAYOFYEAR(birthday) - DAYOFYEAR(NOW()) BETWEEN 0 AND ?)"+
				"or (DAYOFYEAR(birthday) + DAYOFYEAR(CONCAT(YEAR(NOW()),'-12-31')) - DAYOFYEAR(NOW()) < ?)";
		return DbHelp.query(sql, new ScalarHandler<Long>(),param.get("days"),param.get("days")).intValue();
	}

	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class));
	}

	public Customer findById(String custId) {
		String sql = "select * from t_customer where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Customer.class), custId);
	}

	public void update(Customer cust) {
		String sql = "update t_customer set point = ? where id = ?";
		DbHelp.update(sql, cust.getPoint(),cust.getId());
	}


}
