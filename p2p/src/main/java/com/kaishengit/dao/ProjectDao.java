package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Project;
import com.kaishengit.util.DbHelp;

public class ProjectDao {

	public void save(Project pro) {
		String sql = "insert into t_project (projectname,summoney,month,repayrate,startdate,enddate,state,remark) values(?,?,?,?,?,?,?,?)";
		DbHelp.update(sql, pro.getProjectName(),pro.getSumMoney(),pro.getMonth(),pro.getRepayRate(),pro.getStartDate(),pro.getEndDate(),pro.getState(),pro.getRemark());
	}

	/**
	 * 
	 * 获得项目总数
	 * @param key
	 * @param value
	 * @return
	 */
	public int count(String key, String value) {
		String sql = "select count(*) from t_project";
		 List<Object> array = new ArrayList<>();
		if(StringUtils.isNotEmpty(key)) {
			sql = sql + " where " + key + " = ?";
			array.add(value);
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(),array.toArray()).intValue();

	}

	/**
	 * 
	 * 获得项目列表
	 * @param map
	 * @return
	 */
	public List<Project> queryList(HashMap<String, Object> map) {
		String key = map.get("key") == null ? null : String.valueOf(map.get("key"));
		String value = map.get("value") == null ? null : String.valueOf(map.get("value"));
		String sql = "select * from t_project";
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
		
		return DbHelp.query(sql, new BeanListHandler<>(Project.class),array.toArray());
	
	}

	public List<Project> findAllNormalState() {
		//根据状态值判断当前项目是否可以投资 1:正常　２：未开始　３：已结束
		String sql = "select * from t_project where state = 1 ";
		return DbHelp.query(sql, new BeanListHandler<>(Project.class));
	}

	public Project findById(String proId) {
		String sql = "select * from t_project where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Project.class), proId);
	}

	public void update(Project pro) {
		String sql = "update t_project set remainMoney = ?,month= ?,repayrate=?,startdate=? ,enddate=?,state = ? where id = ?";
		DbHelp.update(sql, pro.getRemainMoney(),pro.getMonth(),pro.getRepayRate(),pro.getStartDate(),pro.getEndDate(),pro.getState(),pro.getId());
	}

}
