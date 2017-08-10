package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Company;
import com.kaishengit.util.DbHelp;

public class CompanyDao {

	/**
	 * 获得所有公司
	 * @return
	 */
	public List<Company> findAll() {
		String sql = "select * from t_company";
		return DbHelp.query(sql, new BeanListHandler<>(Company.class));
	}

	/**
	 * 
	 * 通过公司名称获得Id
	 * @param value
	 * @return
	 */
	public Company findByName(String value) {
		String sql = "select * from t_company where name = ?";
		return DbHelp.query(sql, new BeanHandler<>(Company.class), value);
	}

	/**
	 * 保存公司信息
	 * @param company
	 */
	public void save(Company company) {
		String sql = "insert into t_company (name,city,address,tel,remark) values(?,?,?,?,?)";
		DbHelp.update(sql, company.getName(),company.getCity(),company.getAddress(),company.getTel(),company.getRemark());
	}


	/**
	 * 根据参数获得对应的公司列表
	 * @param map
	 * @return
	 */
	public List<Company> queryList(Map<String,Object> map) {
		String key = map.get("key") == null ? null : String.valueOf(map.get("key"));
		String value = map.get("value") == null ? null : String.valueOf(map.get("value"));
		String sql = "select * from t_company";
		 List<Object> array = new ArrayList<>();
		 String where = "";
		if(StringUtils.isNotEmpty(key)) {
			where = where + " where " + key + " = ?";
			array.add(value);
		}
		
		where += " limit ?,?";
		array.add(map.get("start"));
        array.add(map.get("pageSize"));
        sql += where;
		
		return DbHelp.query(sql, new BeanListHandler<>(Company.class, new BasicRowProcessor(new GenerousBeanProcessor())),array.toArray());
	}
	
	/**
	 * 根据筛选条件获得对应公司数量
	 * @param key
	 * @param value
	 * @return
	 */
	public int count(String key, String value) {
		String sql = "select count(*) from t_company";
		 List<Object> array = new ArrayList<>();
		if(StringUtils.isNotEmpty(key)) {
			sql = sql + " where " + key + " = ?";
			array.add(value);
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(),array.toArray()).intValue();

	}

	/**
	 * 通过Id查找对应的公司
	 * @param id
	 * @return
	 */
	public Company findById(int id) {
		String sql = "select * from t_company where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Company.class), id);
	}

	public void deleteById(int id) {
		String sql = "delete from t_company where id = ?";
		DbHelp.update(sql, id);
	}
	
}
