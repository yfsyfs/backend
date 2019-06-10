package com.yfs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.yfs.bean.Person;
import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = applicationContext.getBean(Person.class);
//		Person [name=影法师, age=18, nickname=草]
		System.out.println(person);
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		String property = environment.getProperty("person.nickname");
		// 草
		System.out.println(property);
		applicationContext.close();
	}
}
