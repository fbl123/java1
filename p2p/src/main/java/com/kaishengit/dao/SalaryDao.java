package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.SalarySum;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class SalaryDao {

	public int countByParam(String key, String value) {
		
		String sql = "select COUNT(*) from (select sum(commission),employeeid,date_format( createtime , '%y-%m' ) "
				+ "from t_sal GROUP BY employeeid,date_format( createtime , '%y-%m' ) ";
		List<Object> arrays = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)){
			value = "%" + value + "%";
			sql += "HAVING employeeid IN (SELECT ID FROM t_employee where " + key + " like ?)";
			arrays.add(value);
		}	
				
		sql += " )sumsal";
		
		return DbHelp.query(sql, new ScalarHandler<Long>(),arrays.toArray()).intValue();
	}

	public List<SalarySum> findListByParam(String key, String value, int start, int pageSize) {
		String sql = " select emp.real_name as empName,emp.tel,salsum.sal salSum,salsum.date from t_employee emp,(select SUM(commission) sal ,employeeid,date_format( createtime , '%y-%m' ) as date from t_sal GROUP BY employeeid,date_format(createtime , '%y-%m' )";
		
		List<Object> arrays = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)){
			value = "%" + value + "%";
			sql += "HAVING employeeid IN (SELECT ID FROM t_employee where " + key + " like ?)";
			arrays.add(value);
		}	
		sql += ") salsum where emp.id = salsum.employeeid";
		arrays.add(start);
		arrays.add(pageSize);
		sql += " limit ?,?";
		
		return DbHelp.query(sql, new BeanListHandler<>(SalarySum.class), arrays.toArray());
	}

}
