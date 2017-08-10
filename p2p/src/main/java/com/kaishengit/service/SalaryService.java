package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.SalaryDao;
import com.kaishengit.entity.SalarySum;
import com.kaishengit.util.Page;

public class SalaryService {

	SalaryDao salDao = new SalaryDao();
	public Page<SalarySum> findBySalSum(int pageNo, String key, String value) {
		int count = salDao.countByParam(key,value);
		Page<SalarySum> page = new Page<>(pageNo,count);
		
		List<SalarySum> salSumList = salDao.findListByParam(key,value,page.getStart(),page.getPageSize());
		page.setItems(salSumList);
		return page;
	}

}
