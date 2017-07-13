package com.kaishengit;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

/**
 * 使用java类代替Spring配置文件
 * 整合Mybatis
 * @author Administrator
 *
 */

@Configuration  //替代配置文件
@ComponentScan  //开启自动扫描
@EnableTransactionManagement  //开启事务注解
@PropertySource("classpath:config.properties") //读取properties配置文件
@MapperScan("com.kaishengit.mapper")     //将包中的接口自动扫描并创建实现类放在Spring容器中
public class Spring {
	@Autowired
	private Environment environment; //相当与Properties 类
	
	
	//创造数据库连接池(使用的是dbcp2)
	@Bean
	public DataSource dataSource(){
		BasicDataSource basicDataSource=new BasicDataSource();
		basicDataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		
		return basicDataSource;
	}
	
	//配置事务管理器
	@Bean
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource());
		
	}
	/**
	 * sqlSessionFactory
	 *
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException{
		SqlSessionFactoryBean  factory=new SqlSessionFactoryBean();
		//设置数据源
		factory.setDataSource(dataSource());
		//设置别名包
		factory.setTypeAliasesPackage("com.kaishengit.entity");
		
		
		//加载mapper配置文件
		ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		Resource[] res=resolver.getResources("classpath:mapper/*.xml");
		factory.setMapperLocations(res);
		
		
		//设置
		org.apache.ibatis.session.Configuration config=new org.apache.ibatis.session.Configuration();
		config.setMapUnderscoreToCamelCase(true);  //下滑线变为驼峰命名
		factory.setConfiguration(config);
		
		/**
		 * 添加分页插件
		 * 
		 */
		PageInterceptor pageInterceptor=new PageInterceptor();
		Properties pro=new Properties();
		//设置数据库类型
		pro.setProperty("helperDialect","mysql");
		pageInterceptor.setProperties(pro);
		
		factory.setPlugins(new Interceptor[]{pageInterceptor});
		
		
		
		return factory;
	}
	

}
