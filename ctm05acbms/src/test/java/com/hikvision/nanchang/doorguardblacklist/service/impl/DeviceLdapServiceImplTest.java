package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hikvision.nanchang.doorguardblacklist.common.Page;
import com.hikvision.nanchang.doorguardblacklist.po.Device;
import com.hikvision.nanchang.doorguardblacklist.service.DeviceLdapService;
import com.hikvision.nanchang.doorguardblacklist.test.BaseTest;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description:  DeviceLdapServiceImpl测试类
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public class DeviceLdapServiceImplTest extends BaseTest {

	@Autowired
	private DeviceLdapService deviceLdapService;

	/**
	* @Description: 测试 queryByConditions方法之查询所有 
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryBlacklistDeviceByConditionsWithoutCn() throws Exception {
		Page<Device> page = new Page<>();
		// page.setCur(1);
		page.setCur(2);
		page.setSize(2);
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		page = deviceLdapService.queryBlacklistDeviceByConditions(params);
		for (Device device : page.getData()) {
			System.out.println(device);
		}
	}

	/**
	* @Description: 测试 queryByConditions方法之按设备名称查询 
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryBlacklistDeviceByConditionsWithCn() throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("cn", "6");
		params.put("pageNo", "1");
		params.put("pageSize", "2");
//		params.put("order", "desc");
		Page<Device> page = deviceLdapService.queryBlacklistDeviceByConditions(params);
		for (Device device : page.getData()) {
			System.out.println(device);
		}
	}

	/**
	* @Description: 测试queryDeviceByConditions方法   之查询所有设备（包括开启了黑名单的设备和没有开启黑名单的设备）
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryDeviceByConditionsWithoutCn() {
		List<Device> devices = deviceLdapService.queryDeviceByConditions(null);
		for (Device device : devices) {
			System.out.println(device);
		}
	}

	/**
	* @Description: 测试queryDeviceByConditions方法   之模糊查询
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryDeviceByConditionsWithCn() {
		List<Device> devices = deviceLdapService.queryDeviceByConditions("T");
		for (Device device : devices) {
			System.out.println(device);
		}
	}

	/**
	* @Description: 测试queryByIndexcode方法   
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryByIndexcode() {
		String indexcode = "147ba3f9de1348f2981f6ce91df8aa92";
		Device device = deviceLdapService.queryByIndexcode(indexcode);
		System.out.println(device);
	}

	/**
	* @Description: 测试queryByIndexcode方法   
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryByResourceId() {
		String resourceId = "3d02132a-2eea-4e41-b447-cba1c789f035";
		Device device = deviceLdapService.queryByResourceId(resourceId);
		System.out.println(device);
	}

	/**
	* @Description: 测试add方法   
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testAdd() {
		String resourceId = "a7aa1643-5058-4681-ab06-a42a522ce23d";
		deviceLdapService.add(new String[] { resourceId });
	}

	/**
	* @Description: 测试remove方法   
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testRemove() {
		String resourceId = "3d02132a-2eea-4e41-b447-cba1c789f035";
		deviceLdapService.remove(new String[] { resourceId });
	}

}
