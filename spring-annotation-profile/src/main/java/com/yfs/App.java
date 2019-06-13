package com.yfs;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.yfs.config.MainConfig;

public class App {

	// 如果什么参数都不加, 则启动之后，ioc容器中仅有dataSourceNone和dataSourceDefault
	//第一种使用 profile的方法: -Dspring.profiles.active=prd 表示激活prd环境参数，则只有 dataSourceProd 这个bean和 dataSourceNone会被注入到ioc容器中去
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MainConfig.class);
		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class); // 获取所有DataSource类型的bean，来看一下他们的id是啥
		for (String beanName : beanNamesForType) {
			System.out.println(beanName);
		}
	}
}
