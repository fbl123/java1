package com.kaishengit.mybatis;


import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.MyBatisUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMapperTest {

	private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
	
	private SqlSession sqlSession;
	private UserMapper userMapper;
	
	@Before
	public void before() {
		sqlSession = MyBatisUtil.getSqlSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
	} 
	
	@After
	public void after() {
		sqlSession.close();
	}
	
	@Test
	public void findById() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		//!!! MyBatis���ݶ����Mapper�ӿڶ�̬�����ɸýӿڵ�ʵ����
		//�ӿ�ָ��ʵ����
		//��̬����ģʽ
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findById(1);
		
		logger.debug("user: {}",user);
		
		sqlSession.close();
	}
	
	
	@Test
	public void save() {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setAddress("ŦԼ");
		user.setPassword("777888");
		user.setUserName("Rose");
		
		userMapper.save(user);
		
		System.out.println("ID:" + user.getId());
		
		sqlSession.commit();
		sqlSession.close();
		
		
	}
	
	
	@Test
	public void findAllLoadDept() {
		
		List<User> userList = userMapper.findAllLoadDept();
		
		for(User user : userList) {
			logger.debug("{} -> {}",user.getUserName(),user.getDept().getDeptName());
		}
		
	}
	
	
	@Test
	public void findUserNameAndPassword() {
		User user = userMapper.findByUserNameAndPassword("Tom", "123123");
		System.out.println(user.getId());
	}
	
	
	@Test
	public void findByMapParam() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Tom");
		map.put("pwd", "123123");
		
		User user = userMapper.findByMapParam(map);
		System.out.println(user.getId());
	}
	
	@Test
	public void searchByNameAndAddress() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Tom");
		//map.put("address", "����");
		
		List<User> userList = userMapper.searchByNameAndAddress(map);
		
		for (User user : userList) {
			System.out.println(user.getId());
		}
		
	}
	
	@Test
	public void delByIds() {
		List<Integer> idList = Arrays.asList(12,13);
		
		userMapper.delByIds(idList);
		
		sqlSession.commit();
	}
	
	
	@Test
	public void batchSave() {
		
		List<User> userList = Arrays.asList(new User("Jack","����","111111",1),
				new User("��ɭ��","�¹�","111111",1),
				new User("����","�й�","111111",1));
		
		userMapper.batchSave(userList);
		
		sqlSession.commit();
		
	}
	
	
	@Test
	public void firstLevelCache() {
		
		User user = userMapper.findById(1);
		User user2 = userMapper.findById(1);
		
		System.out.println(user2.getUserName());
		
	}
	
	@Test
	public void secLevelCache() {
		SqlSession session1 = MyBatisUtil.getSqlSession();
		UserMapper userMapper1 = session1.getMapper(UserMapper.class);
		
		User user1 = userMapper1.findById(1);
		
		System.out.println(user1.getUserName());
		session1.close();
		
		System.out.println("-----------------------------");
		
		SqlSession session2 = MyBatisUtil.getSqlSession();
		UserMapper userMapper2 = session2.getMapper(UserMapper.class);
		
		User user2 = userMapper2.findById(1);
		
		System.out.println(user2.getUserName());
		session2.close();
		
	}
	
	
	
	
	
}
