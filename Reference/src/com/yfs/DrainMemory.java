package com.yfs;

/**
* @Description: 榨干内存的类 
* @author: 影法师
* @date: 2019年3月30日 上午9:26:16
*
* @Copyright: 2019 https://yfsyfs.github.io Inc. All rights reserved.
* 注意：本内容仅限于影法师信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class DrainMemory {
	// 消耗大量内存
	public static void drainMemory() {
		// 1M个String
		String[] array = new String[1024 * 1024];
		for (int i = 0; i < 1024 * 10; i++) {
			for (int j = 'a'; j <= 'z'; j++) {
				array[i] += (char) j;
			}
		}
	}
}
