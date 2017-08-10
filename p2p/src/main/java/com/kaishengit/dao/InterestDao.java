package com.kaishengit.dao;

import javax.xml.validation.SchemaFactoryLoader;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Interest;
import com.kaishengit.util.DbHelp;

public class InterestDao {

	public void save(Interest in) {
		String sql  = "insert into t_interest (custid,investid,employeeid,interestsendday,interestmoney,state) values (?,?,?,?,?,?)";
		DbHelp.update(sql,in.getCustId(),in.getInvestId(),in.getEmployeeId(),in.getInterestsendday(),in.getInterrestmoney(),in.getState());
	}

	public int countByInvestIdAndState(int investId, int state) {
		String sql = "select count(*) from t_interest where investid = ? and state =?";
		
		return DbHelp.query(sql, new ScalarHandler<Long>(), investId,state).intValue();
	}

	public void delByIvestId(int investId) {
		String sql = "delete from t_interest where investid = ?";
		DbHelp.update(sql, investId);
	}

	public void delByInvestIdAndState(String investId, int state) {
		String sql = "delete from t_interest where investid = ? and state = ?";
		DbHelp.update(sql, investId, state);
	}

}
