package com.kaishengit.mybatis;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.entity.MovieExample;
import com.kaishengit.entity.MovieExample.Criteria;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.util.MyBatisUtil;

public class MovieMapperTest {

	private SqlSession sqlSession;
	private MovieMapper movieMapper;
	
	@Before
	public void before() {
		sqlSession = MyBatisUtil.getSqlSession();
		movieMapper = sqlSession.getMapper(MovieMapper.class);
	}
	@After
	public void after() {
		sqlSession.close();
	}
	
	@Test
	public void findByYear() {
		/*MovieExample example = new MovieExample();
		Criteria criteria = example.createCriteria();
		criteria.andReleaseyearEqualTo("2010");
		criteria.andTitleEqualTo("���ӵ���");*/
		
		MovieExample example = new MovieExample();
		example.createCriteria().andReleaseyearEqualTo("2011")
								.andTitleIsNotNull()
								.andRateGreaterThanOrEqualTo(9.0F);
		
		
		List<Movie> movieList = movieMapper.selectByExample(example);
		
		for (Movie movie : movieList) {
			System.out.println(movie.getTitle());
		}
		
	}
	
	@Test
	public void findByOr() {
		
		MovieExample example = new MovieExample();
		//example.or().andDaoyanLike("%����������%");
		//example.or().andDaoyanLike("%����%");
		example.createCriteria().andDaoyanIn(Arrays.asList("���������桡","��������"));
		
		List<Movie> movieList = movieMapper.selectByExample(example);
		
		for (Movie movie : movieList) {
			System.out.println(movie.getTitle() + "->" + movie.getDaoyan());
		}
		
	}
	
	@Test
	public void search() {
		//��Ӱ���� ��������  ����
		String movieName = "";
		float minRate = 7.6F;
		float maxRate = 9.0F;
		String daoyan = "������";
		
		MovieExample example = new MovieExample();
		Criteria criteria = example.createCriteria();
		
		if(StringUtils.isNotEmpty(movieName)) {
			criteria.andTitleLike("%"+movieName+"%");
		}
		if(minRate > 0) {
			criteria.andRateGreaterThanOrEqualTo(minRate);
		}
		if(maxRate > 0) {
			criteria.andRateLessThanOrEqualTo(maxRate);
		}
		if(StringUtils.isNoneEmpty(daoyan)) {
			criteria.andDaoyanLike("%"+daoyan+"%");
		}
		
		List<Movie> movieList = movieMapper.selectByExample(example);
		
		for (Movie movie : movieList) {
			System.out.println(movie.getTitle() + "->" + movie.getDaoyan() + "->" + movie.getRate());
		}
		
		
		
		
	}
	
	@Test
	public void page() {
		List<Movie> movieList = movieMapper.selectByPage(10, 5);
		for (Movie movie : movieList) {
			System.out.println(movie.getTitle() + "->" + movie.getDaoyan() + "->" + movie.getRate());
		}
	}
	
	
	@Test
	public void pageHelper() {
		
		MovieExample example = new MovieExample();
		example.createCriteria().andReleaseyearEqualTo("2011");
		
		//PageHelper.startPage(2,5);
		PageHelper.offsetPage(10, 10);
		List<Movie> movieList = movieMapper.selectByExample(example);
		
		for (Movie movie : movieList) {
			System.out.println(movie.getTitle() + "->" + movie.getDaoyan() + "->" + movie.getRate());
		}
		
		PageInfo<Movie> pageInfo = new PageInfo<Movie>(movieList);
		System.out.println("ÿҳ��ʾ������" + pageInfo.getSize());
		System.out.println("��������" + pageInfo.getTotal());
		System.out.println("��ҳ����" + pageInfo.getPages());
		
		
	}
	
	
	
	
	
	
	
	
}
