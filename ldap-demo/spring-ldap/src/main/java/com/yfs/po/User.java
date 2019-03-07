package com.yfs.po;

/**
* @Description: 用户实体类 
* @author: 影法师
* @date: 2019年3月6日 上午7:18:14
*
* @Copyright: 2019 https://yfsyfs.github.io Inc. All rights reserved.
* 注意：本内容仅限于影法师信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class User {

	private String name;

	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + "]";
	}

}
