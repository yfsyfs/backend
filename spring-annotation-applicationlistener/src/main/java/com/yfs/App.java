package com.yfs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
				MainConfig.class);
		annotationConfigApplicationContext.close();
	}
}
/*
收到事件: org.springframework.context.event.ContextRefreshedEvent[source=org.springframework.context.annotation.AnnotationConfigApplicationContext@6f539caf: startup date [Sun Jun 23 17:00:11 CST 2019]; root of context hierarchy]
收到事件: org.springframework.context.event.ContextClosedEvent[source=org.springframework.context.annotation.AnnotationConfigApplicationContext@6f539caf: startup date [Sun Jun 23 17:00:11 CST 2019]; root of context hierarchy]

即监听器收到了两个事件，一个是ContextRefreshedEvent（容器刷新完成事件）和ContextClosedEvent（容器关闭事件），这两个事件都是 ApplicationEvent 的子类	
*/