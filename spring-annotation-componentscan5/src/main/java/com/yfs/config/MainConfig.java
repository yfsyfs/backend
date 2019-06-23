package com.yfs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = "com.yfs", includeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class) // 自定义过滤规则
}, useDefaultFilters = false)
public class MainConfig {

}
