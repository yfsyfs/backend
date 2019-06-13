package com.yfs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yfs.bean.MathCaculator;
import com.yfs.config.MainConfig;

public class App {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		MathCaculator mathCaculator = applicationContext.getBean(MathCaculator.class); // 不能
		System.out.println(mathCaculator.getClass()); // class com.yfs.bean.MathCaculator$$EnhancerBySpringCGLIB$$25294aeb 可见，对于没有实现接口的类，spring采用的是cglib代理(spring内部集成了cglib)
		/*
		 	【开始】
			【结束】
			【正常返回】
			0
		 */
		System.out.println(mathCaculator.div(1, 2));
		/*
		 	【开始】
			【结束】
			【异常】
		 */
		System.out.println(mathCaculator.div(1, 0));
		
		
	}
}
