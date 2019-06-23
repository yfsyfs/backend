package com.yfs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
	}
}
/*
打印 
BeanDefinitionRegistryPostProcessor中postProcessBeanDefinitionRegistry方法的bean定义的数量: 10
BeanDefinitionRegistryPostProcessor中bean定义的数量: 12
bean定义的数量: 12
我会被调用的~
我不会被调用的...
我不会被调用的...
 */
