package com.yfs.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义BeanFactoryPostProcessor
 * 
 * @author yfs
 *
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor的postProcessBeanFactory正在调用...");
		BeanDefinition person1 = beanFactory.getBeanDefinition("person1");
		person1.getPropertyValues().add("nickname", "影法师1");
//		person1.setScope(BeanDefinition.SCOPE_PROTOTYPE); // 设置bean的scope
	}

}
