package com.yfs;

/**
* @Description: 被引用的对象 
* @author: 影法师
* @date: 2019年3月30日 上午9:26:31
*
* @Copyright: 2019 https://yfsyfs.github.io Inc. All rights reserved.
* 注意：本内容仅限于影法师信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class YFSReference {
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("now time : " + System.currentTimeMillis() + " is gc");
	}
}
