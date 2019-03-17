package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hikvision.nanchang.doorguardblacklist.common.Page;
import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistEventService;
import com.hikvision.nanchang.doorguardblacklist.test.BaseTest;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 测试BlacklistEventServiceImpl类
* @author xiexin6  
* @date 2019年3月8日  
*
 */
public class BlacklistEventServiceImplTest extends BaseTest {

	@Autowired
	private BlacklistEventService blacklistEventService;

	/**
	* @Description:测试queryByConditions方法   
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryByConditions() {
		Map<String, Object> params = new HashMap<>();
		params.put("endTime", new Date());
		// 0表示不限，1表示有抓拍图片 2表示无抓拍图片
		params.put("hasPicture", 2);
		Page<BlacklistEvent> page = new Page<>();
		page.setSize(2);
		params.put("page", page);
		List<BlacklistEvent> blacklistEvents = blacklistEventService.queryByConditions(params);
		for (BlacklistEvent blacklistEvent : blacklistEvents) {
			System.out.println(blacklistEvent);
		}
		// 测试缓存是否生效
		blacklistEvents = blacklistEventService.queryByConditions(params);
		for (BlacklistEvent blacklistEvent : blacklistEvents) {
			System.out.println(blacklistEvent);
		}
	}

}
