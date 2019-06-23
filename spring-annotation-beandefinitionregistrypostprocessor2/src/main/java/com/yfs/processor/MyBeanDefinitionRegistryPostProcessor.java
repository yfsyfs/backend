package com.yfs.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import com.yfs.bean.Person;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry正在调用...");
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
		beanDefinitionBuilder.addPropertyValue("nickname", "影法师");
		beanDefinitionBuilder.addPropertyValue("age", 18);
		// 注册第一个bean
		registry.registerBeanDefinition("person1", beanDefinitionBuilder.getBeanDefinition());

		BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Person.class).getBeanDefinition();
		beanDefinition.getPropertyValues().add("nickname", "彩法师");
		beanDefinition.getPropertyValues().add("age", 28);
		// 注册第二个bean
		registry.registerBeanDefinition("person2", beanDefinition);
	}

}
