package com.yfs.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Car implements InitializingBean, DisposableBean {

	public Car() { // 单实例在容器启动的时候创建对象，多实例的话，在每次获取的时候创建对象
		System.out.println("car 构造器被调用...");
	}

	@PostConstruct
	public void xxx() {
		System.out.println("初始化了...");
	}

	@PreDestroy
	public void ooo() {
		System.out.println("销毁了...");
	}

	@Override
	public void destroy() throws Exception { // 此方法在 @PreDestroy 之后执行
		System.out.println("销毁了+1...");
	}

	@Override
	public void afterPropertiesSet() throws Exception { // 此方法在 @PostConstruct 之后执行
		System.out.println("初始化了+1...");
	}

}
