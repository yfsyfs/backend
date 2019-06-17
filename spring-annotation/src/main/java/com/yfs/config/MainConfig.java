package com.yfs.config;

import org.springframework.context.annotation.Bean;

import com.yfs.bean.Person;
// 注意，这里可以没有 @Configuration注解, 因为App并不是用扫描, 而是指定了配置类
public class MainConfig {

	@Bean("wocao") // @Bean注解的作用就是给容器中注册一个bean, 类型是Person, bean的id是用方法名做id
	public Person person() {
		return new Person("lisi", 20);
	}
}