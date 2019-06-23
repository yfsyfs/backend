package com.yfs;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.bean.Person;
import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
				MainConfig.class);
		Map<String, Person> persons = annotationConfigApplicationContext.getBeansOfType(Person.class);
		for (String id : persons.keySet()) {
			System.out.println("bean的id是: " + id);
			System.out.println(persons.get(id));
		}
		annotationConfigApplicationContext.close();
	}
}
/*
BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry正在调用...
BeanFactoryPostProcessor的postProcessBeanFactory正在调用...
postProcessBeforeInitialization正在调用 ,bean的id是org.springframework.context.event.internalEventListenerProcessor
postProcessAfterInitialization正在调用 ,bean的id是org.springframework.context.event.internalEventListenerProcessor
postProcessBeforeInitialization正在调用 ,bean的id是org.springframework.context.event.internalEventListenerFactory
postProcessAfterInitialization正在调用 ,bean的id是org.springframework.context.event.internalEventListenerFactory
postProcessBeforeInitialization正在调用 ,bean的id是mainConfig
postProcessAfterInitialization正在调用 ,bean的id是mainConfig
我不会被调用的...
postProcessBeforeInitialization正在调用 ,bean的id是person1
postProcessAfterInitialization正在调用 ,bean的id是person1
我不会被调用的...
postProcessBeforeInitialization正在调用 ,bean的id是person2
postProcessAfterInitialization正在调用 ,bean的id是person2
bean的id是: person1
Person [nickname=影法师1, age=18]
bean的id是: person2
Person [nickname=彩法师, age=28]
*/