package com.yfs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.config.MainConfig;
import com.yfs.service.UserService;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MainConfig.class);
		UserService userService = applicationContext.getBean(UserService.class);
		userService.insert(); // 调用事务方法
		applicationContext.close();
	}
}
