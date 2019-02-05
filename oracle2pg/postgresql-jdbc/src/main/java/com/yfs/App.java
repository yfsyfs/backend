package com.yfs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// 启动spring容器
		new ClassPathXmlApplicationContext("classpath:application-quartz.xml");
	}

}
