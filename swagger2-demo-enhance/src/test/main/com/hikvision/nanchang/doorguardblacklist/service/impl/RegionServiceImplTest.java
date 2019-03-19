package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hikvision.nanchang.doorguardblacklist.service.RegionService;
import com.hikvision.nanchang.doorguardblacklist.test.BaseTest;
import com.hikvision.nanchang.doorguardblacklist.treevo.RegionTreeVO;

public class RegionServiceImplTest extends BaseTest {
	@Autowired
	private RegionService regionService;

	/**
	* @Description: 测试   query方法之不带name参数
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryWithoutName() {
		List<RegionTreeVO> regionTreeVOs = regionService.query("root000000", null);
		for (RegionTreeVO regionTreeVO : regionTreeVOs) {
			System.out.println(regionTreeVO);
		}
	}

	/**
	* @Description: 测试   query方法之带name参数
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testQueryWithName() {
		List<RegionTreeVO> regionTreeVOs = regionService.query("root000000", "门禁");
		for (RegionTreeVO regionTreeVO : regionTreeVOs) {
			System.out.println(regionTreeVO);
		}
	}
}
