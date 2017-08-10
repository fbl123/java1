package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.kaishengit.dao.CompanyDao;
import com.kaishengit.dao.EmployeeDao;
import com.kaishengit.entity.Company;
import com.kaishengit.entity.Employee;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class EmployeeService {
	EmployeeDao empDao = new EmployeeDao();
	CompanyDao comDao = new CompanyDao();

	/**
	 * 保存员工信息
	 * @param realName 员工姓名
	 * @param idNo 身份证号
	 * @param tel 电话号码
	 * @param deptName 部门名称
	 * @param companyId 公司Id
	 * @throws ServiceException
	 */
	public void saveEmployee(String realName, String idNo, String tel, String deptName, String companyId) 
		throws ServiceException{
		// 封装employee对象
		Employee employee = empDao.findEmpByTel(tel);

		if (employee == null) {
			employee = new Employee();
			employee.setRealName(realName);
			employee.setIdNo(idNo);
			employee.setTel(tel);
			employee.setDeptName(deptName);
			employee.setCompanyId(Integer.parseInt(companyId));
			employee.setPassword(DigestUtils.md5Hex("000000" + Config.get("password.salt") ));

			// 调用dao，进行保存
			empDao.save(employee);
		} else {
			throw new ServiceException("电话号码已存在");
		}

	}

	/**
	 * 通过Id删除员工
	 * @param id
	 */
	public void delEmployeeById(int id) {
		Employee emp = empDao.findById(id);
		if(emp == null) {
			throw new ServiceException("参数错误");
		} else{
			empDao.delById(id);
		}
	}

	/**
	 * 通过Id获得对应的employee
	 * @param id
	 * @return
	 */
	public Employee findEmployeeById(int id) {
		 return empDao.findById(id);
	}

	/**
	 * 根据id修改对应的员工信息
	 * @param id 
	 * @param state 状态
	 * @param companyId 公司Id
	 * @param deptName 部门名称
	 */
	public void editEmployee(String id, String state,  String companyId, String deptName) {
		Employee employee = empDao.findById(Integer.parseInt(id));
		if(employee != null) {
			employee.setCompanyId(Integer.parseInt(companyId));
			employee.setDeptName(deptName);
			employee.setState(Integer.parseInt(state));
			empDao.edit(employee);
		} else{
			throw new ServiceException("系统异常");
		}
	}

	/**
	 * 
	 * 通过用户名和密码登录系统
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	public Employee login(String username, String password ,String ip) {
		//TODO 目前登录只支持电话号码 
		Employee emp = empDao.findEmpByTel(username);
		password = DigestUtils.md5Hex(password + Config.get("password.salt"));
		
		if(emp != null && password.equals(emp.getPassword())) {
			emp.setLastLoginIp(ip);
			//获得当前的登录时间
			emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			empDao.edit(emp);
			return emp;
		} else{
			throw new ServiceException("帐号或者密码错误");
		}
	}


	/**
	 * 通过筛选条件查询对应的数据
	 * @param key
	 * @param value
	 * @param pageNo
	 * @return
	 */
	public Page<Employee> findEmployeeByParams(String key, String value, int pageNo) {
		if(StringUtils.isNotEmpty(key) && key.equals("company_id")) {
			//如果value不是数字，找到对应的Id
			if(!StringUtils.isNumeric(value)){
				Company com = comDao.findByName(value);
				if(com != null) {
					value = String.valueOf(com.getId());
				}
			}
		}
		
		Map<String,Object> maps = Maps.newHashMap();
		maps.put("key", key);
		maps.put("value", value);
		
		int count = empDao.count(maps);
		
		Page<Employee> page = new Page<>(pageNo,count);
		maps.put("start", page.getStart());
		maps.put("pageSize", page.getPageSize());
		List<Employee> empList = empDao.findByParams(maps);
		page.setItems(empList);
		return page;
	}

	
	/**
	 * 验证电话号码是否唯一
	 * @param tel
	 * @return
	 */
	public boolean validateTel(String tel) {
		Employee emp = empDao.findEmpByTel(tel);
		if(emp == null) {
			return true;
		} 
		return false;
	}

}
