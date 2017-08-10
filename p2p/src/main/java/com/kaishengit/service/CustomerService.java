package com.kaishengit.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.kaishengit.dao.CustomerDao;
import com.kaishengit.entity.Customer;
import com.kaishengit.util.Page;

public class CustomerService {
	CustomerDao custDao = new CustomerDao();

	public Page<Customer> findCustomerByQueryParam(int days, int pageNo) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("days", days);
		int count = custDao.count(map);

		Page<Customer> page = new Page<>(pageNo, count);
		map.put("start", page.getStart());
		map.put("pageSize", page.getPageSize());

		List<Customer> custList = custDao.findByParam(map);
		page.setItems(custList);
		return page;
	}

	public List<Customer> findAllCustomer() {
		return custDao.findAll();
	}

	public Customer findCustById(int custId) {

		return custDao.findById(String.valueOf(custId));
	}

}
