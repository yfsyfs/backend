package com.yfs.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableTransactionManagement // 相当于过去的 tx:annotation-driven配置文件(对基于注解的事务进行支持) 即开启基于注解的事务管理功能, 整个必须加，否则事务注解
								// @Transactional 不会生效的
@ComponentScan(basePackages = "com.yfs")
@PropertySource("classpath:db.properties")
@Configuration // 注意, 一定要写 @Configuration
				// 注解，因为这个注解的类中的方法会被区别对待，下面jdbcTemplate()方法中的dataSource方法就不会重复调用,
				// 而只是会到ioc容器中去获取dataSource方法创建的bean
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

	// 两种方式构建jdbcTemplate的bean

	// @Bean // 容器中再放一个用于操作数据库的工具bean---spring-jdbc提供的jdbcTemplate
	// public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	// return new JdbcTemplate(dataSource);
	// }

	@Bean // 容器中再放一个用于操作数据库的工具bean---spring-jdbc提供的jdbcTemplate
	public JdbcTemplate jdbcTemplate(DBConfig config) throws PropertyVetoException {
		return new JdbcTemplate(dataSource(config)); // 这里调用了
	}

	@Bean // 这个bean是一定要有的, 即事务管理器一定要有, 不然报错.
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource) { // 原先配置文件中要写一个事务管理器 tx-managerment, 这个必须有，不然报错
		// spring-jdbc 和 mybatis整合spring要进行事务控制都用下面这个事务管理器, 如果导了 spring-orm，还有jpa、hibernate相关的事务管理器
		return new DataSourceTransactionManager(dataSource); // 和配置文件时写法一样——要配置数据源的, 事务管理器一定要把数据源控住, 这样才能控住数据源中每一条连接,连接的这些回滚啦，事务的开启啦，都由咱们这个事务管理器来做.
	}

}