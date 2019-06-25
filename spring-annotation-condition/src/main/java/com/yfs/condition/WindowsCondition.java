package com.yfs.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {

	/**
	 * context判断条件能使用的上下文,  metadata 是  metadata of the classor method being checked.
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment environment = context.getEnvironment();
		System.out.println(environment.getProperty("os.name"));
		return environment.getProperty("os.name").contains("Windows");
	}

}
