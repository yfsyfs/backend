package com.yfs.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 1. 能获取到ioc使用的bean工厂
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		// 2. 能获取到类加载器
		ClassLoader classLoader = context.getClassLoader();
		// 3. 能获取到当前环境信息(里面封装了运行时jvm环境)
		Environment environment = context.getEnvironment();
		// 4. 能获取到bean定义的注册类——所有bean定义都在这个类里面注册, 我们既可以用它来查询某个bean是否注册，也可以使用它来注册(或者移除)一个bean
		BeanDefinitionRegistry registry = context.getRegistry();
		registry.containsBeanDefinition("person"); // 等等等等，这里我们可以做更多的判断， 这里是判断是否注册了person这个bean
		String os = environment.getProperty("os.name");
		return os.contains("linux");
	}

}
