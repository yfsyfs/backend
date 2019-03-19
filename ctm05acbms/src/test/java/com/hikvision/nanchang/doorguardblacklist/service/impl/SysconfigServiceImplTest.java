package com.hikvision.nanchang.doorguardblacklist.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hikvision.nanchang.doorguardblacklist.po.SysConfig;
import com.hikvision.nanchang.doorguardblacklist.service.SysconfigService;
import com.hikvision.nanchang.doorguardblacklist.test.BaseTest;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 测试 SysconfigServiceImpl
* @author xiexin6  
* @date 2019年3月13日  
*
 */
public class SysconfigServiceImplTest extends BaseTest {

	@Autowired
	private SysconfigService sysconfigService;

	/**
	* @Description: 测试   update方法
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testUpdate() {
		sysconfigService.update("5", "threshold");
	}

	/**
	* @Description: 测试   query方法
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQuery() {
		SysConfig sysConfig = sysconfigService.query("threshold");
		System.out.println(sysConfig);
		// 验证测试缓存生效
		System.out.println(sysconfigService.query("threshold"));
		sysconfigService.update("8", "threshold");
		// 测试缓存是否生效
		System.out.println(sysconfigService.query("threshold"));
	}
}
