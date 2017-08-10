package com.kaishengit.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.kaishengit.dao.CompanyDao;
import com.kaishengit.dao.EmployeeDao;
import com.kaishengit.entity.Company;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class CompanyService {

	CompanyDao comDao = new CompanyDao();
	EmployeeDao empDao = new EmployeeDao();
	/**
	 * 获得所有的城市列表
	 * @return
	 */
	public List<String> getCity() {
		String city = Config.get("company.city");
		System.out.println(city);
		String[] cityNames = city.split("-");
		List<String> cities = Arrays.asList(cityNames);
		return cities;
	}

	/**
	 * 新增公司信息
	 * @param companyName
	 * @param phone
	 * @param city
	 * @param address
	 * @param remark
	 */
	public void addCompany(String companyName, String phone, String city, String address, String remark) {
		Company company = new Company();
		company.setName(companyName);
		company.setTel(phone);
		company.setCity(city);
		company.setAddress(address);
		company.setRemark(remark);
		comDao.save(company);

	}

	/**
	 * 
	 * 根据条件查询对应页码的公司数据
	 * @param pageNo
	 * @param key
	 * @param value
	 * @return
	 */
	public Page<Company> getList(Integer pageNo, String key, String value) {
		HashMap<String,Object> map = Maps.newHashMap();
        int count = comDao.count(key,value);
        
        Page<Company> companyPage = new Page<>(pageNo,count);
        map.put("key",key);
        map.put("value",value);
        map.put("start",companyPage.getStart());
        map.put("pageSize",companyPage.getPageSize());

        List<Company> comList = comDao.queryList(map);
        companyPage.setItems(comList);
        return companyPage;
	}

	/**
	 * 根据名称获得公司信息
	 * @param companyName
	 * @return
	 */
	public Company findByName(String companyName) {
		return comDao.findByName(companyName);
	}

	/**
	 * 校验公司名称是否可用
	 * @param companyName
	 * @return
	 */
	public boolean validateComopanyName(String companyName) {
		
		return comDao.findByName(companyName) == null;
	}
	
	/**
	 * 获得公司信息列表
	 * @return
	 */
	public List<Company> getCompanyList() {
		return comDao.findAll();
	}

	/**
	 * 获得部门名称列表
	 * @return
	 */
	public List<String> getDeptNameList() {
		return Arrays.asList(Config.get("dept.name.list").split("-"));
	}

	/**
	 * 
	 * 通过id删除对应的分公司，如果公司下还有员工，提示不可删除
	 * @param id
	 */
	public void delCompanyById(int id) {
		Company com = comDao.findById(id);
		if(com != null) {
			Map<String,Object> maps = Maps.newHashMap();
			maps.put("key", "company_id");
			maps.put("value", com.getId());
			int count = empDao.count(maps);
			if(count == 0) {
				comDao.deleteById(id);
			} else {
				throw new ServiceException("公司名下还有员工，不可删除");
			}
		} else {
			throw new ServiceException("参数错误");
		}
	}

}
