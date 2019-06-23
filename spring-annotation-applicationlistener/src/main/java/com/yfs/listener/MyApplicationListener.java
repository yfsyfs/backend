package com.yfs.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义Spring容器事件(监听ApplicationEvent这种类型的事件)监听器
 * 
 * @author yfs
 *
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

	/**
	 * 当容器中发布此事件之后，方法触发
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("收到事件: " + event);
	}

}
