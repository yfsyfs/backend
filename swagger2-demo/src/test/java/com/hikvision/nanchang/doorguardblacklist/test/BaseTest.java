package com.hikvision.nanchang.doorguardblacklist.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 测试基类
* @author xiexin6  
* @date 2019年3月8日  
*
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-ldap.xml", "classpath:application-context.xml",
		"classpath:spring-redis.xml" })
public abstract class BaseTest {

}
