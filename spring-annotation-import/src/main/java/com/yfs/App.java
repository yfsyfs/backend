package com.yfs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		// 通过注解获取ioc容器, 原先通过的是 ClassPathXmlApplicationContext(即配置文件的方式)获取IOC容器
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		// 有 com.yfs.bean.Color和 com.yfs.bean.Human了
		printBeans((AnnotationConfigApplicationContext) applicationContext);
	}
	
	private static void printBeans(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
}
