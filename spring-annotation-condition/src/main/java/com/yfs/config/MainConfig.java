package com.yfs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import com.yfs.bean.Person;
import com.yfs.condition.LinuxCondition;
import com.yfs.condition.WindowsCondition;

public class MainConfig {

	@Bean("linus")
	@Conditional(LinuxCondition.class) // 只有LinuxCondition的matches方法返回true的话, 此bean才会被加入到ioc容器中去， 注意，@Conditional不仅能放在方法上，而且还可以放在整个类上，如果放在类上，则这个类中配置的所有bean注册都能生效
	public Person linus() {
		return new Person("linus", 48);
	}
	
	@Bean("billgates") 
	@Conditional(WindowsCondition.class) // 只有WindowsCondition的matches方法返回true的话, 此bean才会被加入到ioc容器中去
	public Person billgates() {
		return new Person("billgates", 62);
	}
}
