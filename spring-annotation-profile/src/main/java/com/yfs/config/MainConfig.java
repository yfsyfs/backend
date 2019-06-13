package com.yfs.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@PropertySource("classpath:db.properties")
public class MainConfig implements EmbeddedValueResolverAware {

	@Value("${db.user}")
	private String user;

	/** 值解析器 */
	private StringValueResolver resolver;

	private String driverclass;
	
	@Bean // 没有 @Profile注解的bean 不论什么情况下都会被注入到ioc中去
	public DataSource dataSourceNone() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/devp");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Bean
	@Profile("default")
	public DataSource dataSourceDefault() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/devp");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}

	@Bean
	@Profile("test")
	public DataSource dataSourceTest(@Value("${db.password}") String password) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setDriverClass(driverclass);
		return dataSource;
	}

	@Bean
	@Profile("dev")
	public DataSource dataSourceDevp() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/devp");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}

	@Bean
	@Profile("prd")
	public DataSource dataSourceProd() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.resolver = resolver;
		driverclass = resolver.resolveStringValue("${db.driverclass}");
	}

}