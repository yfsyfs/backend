package com.yfs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.yfs.aspects.LogAspect;
import com.yfs.bean.MathCaculator;

@EnableAspectJAutoProxy // 类比于原先注解版本的aop中的要引入aop命名空间并且加上 <aop:aspectj-autoproxy></aop:aspectj-autoproxy> 开启基于注解版的切面功能, 这里因为纯java了, 所以不需要
public class MainConfig {

	@Bean
	public MathCaculator mathCaculator() { // 加入业务逻辑组件
		return new MathCaculator();
	}

	@Bean
	public LogAspect logAspect() { // 加入切面组件
		return new LogAspect();
	}
}