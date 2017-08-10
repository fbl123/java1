package com.kaishengit.dao;

import com.kaishengit.entity.Commission;
import com.kaishengit.util.DbHelp;

public class CommissionDao {

	public void save(Commission commission) {
		String sql = "insert into t_sal(employeeid,investid,commission) values (?,?,?)";
		DbHelp.update(sql, commission.getEmployeeId(),commission.getInvestId(),commission.getCommission());
	}

	public void delByInvestId(int investId) {
		String sql = "delete from t_sal where investid = ?";
		DbHelp.update(sql, investId);
	}

}
