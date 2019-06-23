package com.yfs.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

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
		System.out.println("bean定义的数量: " + beanFactory.getBeanDefinitionCount());
	}

}
