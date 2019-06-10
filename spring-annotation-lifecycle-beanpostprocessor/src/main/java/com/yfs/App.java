package com.yfs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		printBeans((AnnotationConfigApplicationContext) applicationContext);
		((AnnotationConfigApplicationContext)applicationContext).close(); // 关闭ioc, 销毁里面所有的bean
	}
	
	private static void printBeans(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
}
