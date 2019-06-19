package com.yfs.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableTransactionManagement
@ComponentScan(basePackages = "com.yfs")
@PropertySource("classpath:db.properties")
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true) // 开启自动代理, 并且暴露当前线程代理对象到 AopContext中去, 相当于是 <aop:aspectj-autoproxy> 中多一个 expose-prox=true 的属性
public class MainConfig {

	@Bean // 配置数据源
	public DataSource dataSource(DBConfig config) throws PropertyVetoException {
		System.out.println("数据源被加载了...");
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(config.getUser());
		dataSource.setPassword(config.getPassword());
		dataSource.setJdbcUrl(config.getJdbcUrl());
		dataSource.setDriverClass(config.getDriverClass());
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DBConfig config) throws PropertyVetoException {
		return new JdbcTemplate(dataSource(config));
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}