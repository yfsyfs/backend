package com.yfs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.yfs.bean.Person;

@ComponentScan("com.yfs.processor")
public class MainConfig {

	@Bean("wocao")
	public Person person() {
		return new Person("lisi", 20);
	}
	
}