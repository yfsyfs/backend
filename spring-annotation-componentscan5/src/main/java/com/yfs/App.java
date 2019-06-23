package com.yfs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] ids = applicationContext.getBeanDefinitionNames(); // 获取ioc容器中所有bean的id
		/* 
		 */
		for (String id : ids) { // 注意 myTypeFilter 也会进来, 因为 @ComponentScan 中写的包名是com.yfs, 所以下面每一个类都会进来进行匹配
			System.out.println("---" + id);
		}
	}
}
