package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hikvision.nanchang.doorguardblacklist.common.Page;
import com.hikvision.nanchang.doorguardblacklist.po.BlackListPerson;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistPersonService;
import com.hikvision.nanchang.doorguardblacklist.test.BaseTest;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description:  BlacklistPersonServiceImpl测试类
* @author xiexin6  
* @date 2019年3月11日  
*
 */
public class BlacklistPersonServiceImplTest extends BaseTest {

	@Autowired
	private BlacklistPersonService blacklistPersonService;

	/**
	* @Description: 测试   queryByConditions 方法
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryByConditions() {
		Map<String, Object> params = new HashMap<>();
		params.put("id", "360102199410233831");
		params.put("type", 0);
		Page<BlackListPerson> page = new Page<>();
		page.setCur(1);
		page.setSize(2);
		params.put("page", page);
		List<BlackListPerson> blackListPersons = blacklistPersonService.queryByConditions(params);
		for (BlackListPerson blackListPerson : blackListPersons) {
			System.out.println(blackListPerson);
		}
	}

	/**
	* @Description: 测试   insert 方法 之插入临时黑名单
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testInsert_tmp() {
		BlackListPerson blackListPerson = new BlackListPerson("360102199410233831", "影法師", 0, null);
		blacklistPersonService.insert(blackListPerson);
	}

	/**
	* @Description: 测试   insert 方法 之插入永久黑名单
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testInsert_permanent() {
		BlackListPerson blackListPerson = new BlackListPerson("360102199510233831", "彩法師", 1, "通缉犯");
		blacklistPersonService.insert(blackListPerson);
	}

}
