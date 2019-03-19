package com.hikvision.nanchang.doorguardblacklist.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiJsonProperty {

	String key(); // key

	String example() default "";// 示例

	String type() default "string"; // 支持string、int、double

	String description() default "";// 参数描述

	boolean required() default true; // 是否必传

}
