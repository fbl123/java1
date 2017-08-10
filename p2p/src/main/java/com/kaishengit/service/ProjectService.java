package com.kaishengit.service;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Maps;
import com.kaishengit.dao.ProjectDao;
import com.kaishengit.entity.Company;
import com.kaishengit.entity.Project;
import com.kaishengit.util.Page;

public class ProjectService {

	ProjectDao proDao = new ProjectDao();
	
	/**
	 * 保存项目信息
	 * 
	 * @param projectName
	 * @param sumMoney
	 * @param repayrate
	 * @param months
	 * @param beginDate
	 * @param endDate
	 */
	public void saveProject(String projectName, String sumMoney, String repayrate, String months, String beginDate,
			String endDate, String remark) {
		Project pro = new Project();
		
		pro.setProjectName(projectName);
		pro.setSumMoney(Double.parseDouble(sumMoney));
		pro.setMonth(Integer.parseInt(months));
		pro.setRepayRate(Double.parseDouble(repayrate));
		pro.setRemark(remark);
		pro.setStartDate(beginDate);
		pro.setEndDate(endDate);
		
		proDao.save(pro);
		
		
	}


	public Page<Project> getProjectList(Integer pageNo, String key, String value) {

		HashMap<String,Object> map = Maps.newHashMap();
        int count = proDao.count(key,value);
        
        Page<Project> page = new Page<>(pageNo,count);
        map.put("key",key);
        map.put("value",value);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());

        List<Project> topicList = proDao.queryList(map);
        page.setItems(topicList);
        return page;
	}


	/**
	 * 
	 * 获得所有状态为正常的项目列表
	 * @return 所有状态正常的项目列表
	 */
	public List<Project> findAllProject() {
		return proDao.findAllNormalState();
	}


	/**
	 * 根据项目id获得项目信息
	 * @param proId
	 * @return project对象
	 */
	public Project findProjectById(String proId) {
		return proDao.findById(proId);
	}


}
