package com.yfs.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器——用于初始化前后进行处理工作
 * 
 * @author yfs
 *
 */
@Component // 为了能让此后置处理器工作，我们需要将此后置处理器加入到ioc中去
public class MyBeanPostProcessor implements BeanPostProcessor {

	/**
	 * 此接口方法用于 Apply this BeanPostProcessor to the given new bean instance before
	 * any bean initialization callbacks (like InitializingBean's afterPropertiesSet
	 * or a custom init-method, 其实还包括 @PostConstruct). The bean will already be
	 * populated with property values. 参数一: bean 就是ioc帮我们创建好的实例，尚未初始化 参数二: beanName
	 * 就是bean在ioc容器中的id 此方法返回the bean instance to use, either the original or a
	 * wrapped one;
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("初始化前 : " + beanName + "==>" + bean);
		return bean;
	}

	/**
	 * 此接口方法用于 Apply this BeanPostProcessor to the given new bean instance
	 * <i>after</i> any bean initialization callbacks (like InitializingBean's
	 * {@code afterPropertiesSet} or a custom init-method). The bean will already be
	 * populated with property values.
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("初始化后 : " + beanName + "==>" + bean);
		return bean;
	}

}
