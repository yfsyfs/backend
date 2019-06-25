package com.yfs;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.bean.Person;
import com.yfs.config.MainConfig;

public class App {

	/**
	 * 在Windows机器上只有 billgates这个bean被加载，如果以 -Dos.name=linux的vm参数运行的话,则只有 linus这个bean被加载进ioc容器 
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
		
		Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
		System.out.println(beansOfType);
	}
}
