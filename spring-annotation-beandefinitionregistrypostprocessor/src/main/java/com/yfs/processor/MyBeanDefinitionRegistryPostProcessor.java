package com.yfs.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import com.yfs.bean.Person;

/**
 * 自定义 BeanDefinitionRegistryPostProcessor，它是BeanFactoryPostProcessor的子接口
 * 
 * @author yfs
 *
 */
@Component // 别忘了注入
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	/**
	 * BeanFactoryPostProcessor接口的方法
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanDefinitionRegistryPostProcessor中bean定义的数量: " + beanFactory.getBeanDefinitionCount());
	}

	/**
	 * 执行时机在BeanFactoryPostProcessor接口的执行之前,
	 * 因为BeanFactoryPostProcessor是所有的bean定义已经被加载，而BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry方法的执行时机是在所有bean定义被加载之前（关于这一点可以参见此接口方法的注释）
	 * 
	 * 这里的入参其实是 DefaultListableBeanFactory（即ioc容器）,和上面的 方法的入参beanFactory是同一个实例
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("BeanDefinitionRegistryPostProcessor中postProcessBeanDefinitionRegistry方法的bean定义的数量: "
				+ registry.getBeanDefinitionCount());
		// 我们可以使用BeanDefinitionRegistryPostProcessor接口注册自己的组件
		BeanDefinition beanDefinition = new RootBeanDefinition(Person.class);
		registry.registerBeanDefinition("hello", beanDefinition);
		// 另一种写法
		AbstractBeanDefinition beanDefinition2 = BeanDefinitionBuilder.rootBeanDefinition(Person.class).getBeanDefinition();
		registry.registerBeanDefinition("hello2", beanDefinition2);
	}

}
