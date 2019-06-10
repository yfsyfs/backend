package com.yfs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.yfs.bean.Person;

@PropertySource("classpath:person.properties") // 使用 @PropertySource(value可以写数组) 加载外部的配置文件中的键值对进入运行时的环境变量中
public class MainConfig {

	@Bean
	public Person person() {
		return new Person();
	}
}