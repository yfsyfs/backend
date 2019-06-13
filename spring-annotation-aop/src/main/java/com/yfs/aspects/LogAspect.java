package com.yfs.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 日志切面类
 * 
 * @author yfs
 *
 */
@Aspect // 必须要告诉ioc容器，我是一个切面类
public class LogAspect {

	@Pointcut("execution(public int com.yfs.bean.MathCaculator.*(..))") // MathCaculator中所有的public int 的方法
	public void xxx() {

	}

	/**
	 * 目标方法开始
	 */
	@Before("xxx()") // 注意，如果是本切面类中引用xxx的话, 直接写xxx即可，如果别的切面类需要引用本切面类的xxx这个切入点的话, 需要全限定名，即
						// com.yfs.aspects.LogAspect.xxx()
	// @Before("public int com.yfs.bean.MathCaculator.*(..)") // 表示所有方法
	public void logStart(JoinPoint joinpoint) { // 这里有必要说一下，就是 JoinPoint 入参如果有的话，一定要写在第一位, 不然spring还是无法解析到的
		System.out.println(
				// 【方法div开始，参数是[1, 2]】
				"【方法" + joinpoint.getSignature().getName() + "开始，参数是" + Arrays.asList(joinpoint.getArgs()) + "】");
	}

	/**
	 * 目标方法结束(不论正常异常都会被调用)
	 */
	@After("xxx()")
	public void logEnd() {
		System.out.println("【结束】");
	}

	/**
	 * 目标方法运行正常结束
	 */
	@AfterReturning(value = "xxx()", returning = "yyy") // returning指定谁来接收目标方法的返回值
	public void logReturn(Object yyy) {
		// 【正常返回, 返回值是0】
		System.out.println("【正常返回, 返回值是" + yyy + "】");
	}

	/**
	 * 目标方法运行出现异常
	 */
	@AfterThrowing(value = "xxx()", throwing = "eee") // throwing和上面的returning道理是一样的
	public void logException(Exception eee) {
		// 【异常】/ by zero
		System.out.println("【异常】" + eee.getMessage());
	}

}
