package com.yfs.processor;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import com.yfs.bean.Person;

/**
 * 自定义BeanFactoryPostProcessor
 * 
 * @author yfs
 *
 */
@Component // 自定义BeanFactoryPostProcessor要能用起来，一定要加在容器中
public class MyBeanPostFactoryProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println(beanFactory.getBeanDefinitionCount()); // 看看目前此时ioc容器中有几个bean被定义了
		System.out.println(Arrays.asList(beanFactory.getBeanDefinitionNames())); // 看看目前此时ioc容器中有几个bean定义
		System.out.println("bean后置处理器的数量: " + beanFactory.getBeanPostProcessorCount());
		System.out.println(beanFactory.getBean(Person.class));
	}

}
