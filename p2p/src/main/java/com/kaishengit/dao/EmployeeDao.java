package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Company;
import com.kaishengit.entity.Employee;
import com.kaishengit.util.DbHelp;

public class EmployeeDao {

	public void save(Employee employee){
		String sql = "insert into t_employee (real_name,idNo,tel,password,company_id,dept_name,state)values(?,?,?,?,?,?,?)";
		DbHelp.update(sql, employee.getRealName(),employee.getIdNo(),employee.getTel(),employee.getPassword(),employee.getCompanyId(),employee.getDeptName(),employee.getState());
	}

	public Employee findEmpByTel(String tel) {
		String sql = "select * from t_employee where tel = ?";
		return DbHelp.query(sql, new BeanHandler<>(Employee.class,new BasicRowProcessor(new GenerousBeanProcessor())), tel);
		
	}

	public List<Employee> findAll() {
		String sql = "select * from t_employee";
		return DbHelp.query(sql, new BeanListHandler<>(Employee.class,new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	public Employee findById(int id) {
		String sql = "select * from t_employee where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Employee.class,new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public void delById(int id) {
		String sql = "delete from t_employee where id = ?";
		DbHelp.update(sql, id);
	}

	public void edit(Employee emp) {
		String sql = "update t_employee set real_name = ?,idNo=?, tel = ?, company_id = ?,dept_name = ? ,state = ?,last_login_time=?,last_login_ip=? where id = ?";
		DbHelp.update(sql, emp.getRealName(),emp.getIdNo(),emp.getTel(),emp.getCompanyId(),emp.getDeptName(),emp.getState(),emp.getLastLoginTime(),emp.getLastLoginIp(),emp.getId());
	}


	public List<Employee> findByParams(Map<String, Object> maps) {
		//拼装sql
		String sql = "select e.*,c.name companyName from t_employee e left join t_company c on e.company_id = c.id";
		List<Object> lists = Lists.newArrayList(); 
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		String where = "";
		
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where e." + key + " = ?";//where tel = ?
			lists.add(maps.get("value"));
		}
		
		where += " limit ?,?";//where tel = ? limit ?,?
		lists.add(maps.get("start"));
		lists.add(maps.get("pageSize"));
		sql += where;
		
		return DbHelp.query(sql, new AbstractListHandler<Employee>() {
			
			@Override
			protected Employee handleRow(ResultSet rs) throws SQLException {
//				new GenerousBeanProcessor()   BeanProcessor()
				Employee emp = new GenerousBeanProcessor().toBean(rs, Employee.class);
				Company com = new Company();
				com.setName(rs.getString("companyName"));
				emp.setCompany(com);
				return emp;
			}
		},lists.toArray());
	}
	
	
	public int count(Map<String, Object> maps) {
		String sql = "select count(*) from t_employee";
		String where = "";
		List<Object> lists = Lists.newArrayList(); 
		
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where " + key + " = ?";//where tel = ?
			lists.add(maps.get("value"));
		}
		sql += where;
		return DbHelp.query(sql, new ScalarHandler<Long>(),lists.toArray()).intValue();
	}

	

	/*public Employee findByName(String username) {
		String sql = "select * from t_employee where tel"
		return null;
	}*/
	
	
}
