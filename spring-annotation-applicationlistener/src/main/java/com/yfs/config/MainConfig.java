package com.yfs.config;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.yfs.listener")
public class MainConfig {
	// 本配置类的作用仅仅是扫包, bean的定义在 MyBeanDefinitionRegistryPostProcessor中完成
}