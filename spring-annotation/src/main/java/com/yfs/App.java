package com.yfs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.bean.Person;
import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		// 通过注解获取ioc容器, 原先通过的是 ClassPathXmlApplicationContext(即配置文件的方式)获取IOC容器
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = applicationContext.getBean(Person.class);
//		打印 Person [name=lisi, age=20]
		System.out.println(person);
		String[] ids = applicationContext.getBeanNamesForType(Person.class); // 根据bean的类型找到bean的id
		for (String id : ids) {
			System.out.println(id); // 打印 wocao
		}
	}
}
