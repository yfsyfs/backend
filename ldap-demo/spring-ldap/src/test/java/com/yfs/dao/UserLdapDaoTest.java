package com.yfs.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yfs.po.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-ldap.xml" })
public class UserLdapDaoTest {

	@Autowired
	private UserLdapDao userLdapDao;

	/**
	* @Description: 测试增 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:35:50
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void testAdd() {
		User user = new User();
		user.setId("haha");
		user.setName("xixi");
		userLdapDao.add("uid=xili,ou=Developer", user);
	}

	/**
	* @Description: 测试删 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:50:39
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void testDelete() {
		userLdapDao.delete("uid=xili,ou=Developer");
	}

	/**
	* @Description: 测试重命名 (只支持叶子条目)
	* @author: 影法师
	* @date: 2019年3月6日 上午7:52:16
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void testRename() {
		userLdapDao.modifyRDN("ou=3法师组织南昌分舵", "ou=谢鑫");
	}

	/**
	* @Description: 修改条目的属性（不局限于叶子条目） 
	* @author: 影法师
	* @date: 2019年3月6日 上午9:23:24
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void testModifyAttr() {
		User user = new User();
		user.setId("123243");
		user.setName("彩法师");
		userLdapDao.modfiyAttrs("ou=Developer", user);
	}

	/**
	* @Description: 新增条目属性
	* @author: 影法师
	* @date: 2019年3月6日 上午9:23:24
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void testAddAttr() {
		User user = new User();
		user.setId("123243");
		user.setName("彩法师");
		userLdapDao.addAttrs("ou=Developer", user);
	}

	/**
	* @Description: 删除条目属性
	* @author: 影法师
	* @date: 2019年3月6日 上午9:23:24
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void testDelAttr() {
		User user = new User();
		user.setId("123243");
		user.setName("彩法师");
		userLdapDao.delAttrs("ou=Developer", user);
	}

	/**
	* @Description: 测试查 
	* @author: 影法师
	* @date: 2019年3月6日 上午9:46:54
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void testFind() {
		// 查找某个特定的叶子条目
		User user = userLdapDao.find("uid=michael,ou=Demo,dc=app1");
		// User [name=Sun, id=admin]
		System.out.println(user);
	}

	@Test
	public void testSearch() {
		// 查询 dc=app1,dc=yfs,dc=com 这个条目下所有拥有属性sn, 并且属性值等于Sun的条目（不管是不是叶子条目）
		List<User> users = userLdapDao.search("dc=app1", "sn=Sun");
		for (User user : users) {
			// User [name=Kai, id=michael]
			System.out.println(user);
		}
	}

}
