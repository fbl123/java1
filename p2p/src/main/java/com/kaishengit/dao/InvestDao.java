package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Invest;
import com.kaishengit.util.DbHelp;

public class InvestDao {

	public int save(Invest invest) {
		String sql = "insert into t_invest (proid,custid,employeeid,rate,month,investmoney,signdate,enddate,state) values (?,?,?,?,?,?,?,?,?)";
		return DbHelp.insert(sql, new ScalarHandler<Long>(), invest.getProId(),invest.getCustId(),invest.getEmployeeId(),invest.getRate(),invest.getMonth(),invest.getInvestMoney(),invest.getSignDate(),invest.getEndDate(),invest.getState()).intValue();
	}

	public int countByparam(String key, String value) {
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)){
			value = "%" + value + "%";
			String sql = "select count(*) from t_invest as inv ,t_customer as cust,t_project as pro where inv.proid = pro.id and inv.custid = cust.id and " + key +" like ?";
			// select count(*) from t_invest where name like '%zhang%'
			return DbHelp.query(sql, new ScalarHandler<Long>(), value).intValue();
		} else{
			String sql = "select count(*) from t_invest";
			return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
		}
		
		
	}

	public List<Invest> findListByparam(String key, String value, int start, int pageSize) {
		String sql = "select inv.*,cust.`name` ,emp.real_name as realName ,pro.projectname  from t_invest as inv ,t_customer as cust,t_employee as emp,t_project as pro where inv.proid = pro.id and inv.custid = cust.id and inv.employeeid = emp.id";
		List<Object> arrays = Lists.newArrayList();
		
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			value = "%" + value + "%";
			sql += " and " + key + " like ?";
			//  and name like '%张三%'
			arrays.add(value);
		}
		
		sql += " limit ?,?";
		arrays.add(start);
		arrays.add(pageSize);
		
		return DbHelp.query(sql, new BeanListHandler<>(Invest.class), arrays.toArray());
	}

	public Invest findById(int investId) {
		String sql = "select * from t_invest where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Invest.class), investId);
	}

	public void delById(int investId) {
		String sql = "delete from t_invest where id = ?";
		DbHelp.update(sql, investId);
	}

	public void update(Invest invest) {
		String sql = "update t_invest set state = ? where id = ?";
		DbHelp.update(sql,invest.getState(),invest.getId());
	}

	
}
