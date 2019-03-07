package com.yfs.dao;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import com.yfs.po.User;

/**
* @Description: 用户LDAP的dao层 
* @author: 影法师
* @date: 2019年3月6日 上午7:19:12
*
* @Copyright: 2019 https://yfsyfs.github.io Inc. All rights reserved.
* 注意：本内容仅限于影法师信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class UserLdapDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	/**
	* @Description: 增 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:36:00
	* @param: @param rdn
	* @param: @param user
	* @return: void
	* @throws
	 */
	public void add(String rdn, User user) {
		Attributes attrs = new BasicAttributes();
		Attribute ocAttr = new BasicAttribute("objectClass");//获取objectclass相关的一个东西通过这个东西给条目添加objectclass
		ocAttr.add("inetOrgPerson");//设置objectClass
		attrs.put(ocAttr);
		Attribute snAttr = new BasicAttribute("sn");
		Attribute cnAttr = new BasicAttribute("cn");
		snAttr.add(user.getName());//给sn赋值
		cnAttr.add(user.getId());//给cn赋值
		attrs.put(snAttr);//把sn放入到属性当中
		attrs.put(cnAttr);
		this.ldapTemplate.bind(rdn, null, attrs);
	}

	/**
	* @Description: 删 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:36:06
	* @param: @param rdn
	* @return: void
	* @throws
	 */
	public void delete(String rdn) {
		this.ldapTemplate.unbind(rdn);
	}

	/**
	* @Description: 改(条目rdn) 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:36:12
	* @param: @param oRDN
	* @param: @param nRDN
	* @return: void
	* @throws
	 */
	public void modifyRDN(String oRDN, String nRDN) {
		this.ldapTemplate.rename(oRDN, nRDN);
	}

	/**
	* @Description: 改（修改条目属性） 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:36:30
	* @param: @param rdn
	* @param: @param user
	* @return: void
	* @throws
	 */
	public void modfiyAttrs(String rdn, User user) {
		// 注意, 这个属性允许写什么 需要查看OpenLDAP服务器的scheme文件
		Attribute attr = new BasicAttribute("userPassword");
		attr.add(user.getName());
		ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
		this.ldapTemplate.modifyAttributes(rdn, new ModificationItem[] { item });
	}

	/**
	* @Description: 改（新增条目属性） 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:36:30
	* @param: @param rdn
	* @param: @param user
	* @return: void
	* @throws
	 */
	public void addAttrs(String rdn, User user) {
		// 注意, 这个属性允许写什么 需要查看OpenLDAP服务器的scheme文件
		// 注意, telephoneNumber 这个属性只能是数字, 不能是普通的字符串 不然会报
		// InvalidaPropertyValue 异常的  这其实也是OpenLDAP 的scheme约束决定的
		Attribute attr = new BasicAttribute("telephoneNumber");
		attr.add(user.getId());
		ModificationItem item = new ModificationItem(DirContext.ADD_ATTRIBUTE, attr);
		this.ldapTemplate.modifyAttributes(rdn, new ModificationItem[] { item });
	}

	/**
	* @Description: 改（删除条目属性） 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:36:30
	* @param: @param rdn
	* @param: @param user
	* @return: void
	* @throws
	 */
	public void delAttrs(String rdn, User user) {
		Attribute attr = new BasicAttribute("telephoneNumber");
		// 注意, 如果不指定属性值的话, 则就删除 telephoneNumber的属性, 如果指定, 则一定要指定对,不然异常
		//		attr.add(user.getId());
		ModificationItem item = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr);
		this.ldapTemplate.modifyAttributes(rdn, new ModificationItem[] { item });
	}

	/**
	* @Description: 查 (查询某个特定的叶子条目, 可以理解为关系型数据库中根据id查询)
	* @author: 影法师
	* @date: 2019年3月6日 上午7:36:47
	* @param: @param rdn
	* @param: @return
	* @return: User
	* @throws
	 */
	public User find(String rdn) {
		return (User) this.ldapTemplate.lookup(rdn, new AttributesMapper() {
			// 映射规则
			public Object mapFromAttributes(Attributes attributes) throws NamingException {
				User user = new User();
				if (attributes != null) {
					Attribute idAttr = attributes.get("uid");
					if (idAttr != null) {
						// 因为一个属性可以有多个值
						user.setId((String) idAttr.get(0));
					}
					Attribute nameAttr = attributes.get("sn");
					if (nameAttr != null) {
						user.setName((String) nameAttr.get());
					}
				}
				return user;
			}
		});
	}

	/**
	* @Description: 查(按过滤条件查询列表) 
	* @author: 影法师
	* @date: 2019年3月6日 上午7:37:07
	* @param: @param rdn 查询哪个根条目下的
	* @param: @param filter
	* @param: @return
	* @return: List<User>
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public List<User> search(String rdn, String filter) {
		return this.ldapTemplate.search(rdn, filter, new AttributesMapper() {
			public Object mapFromAttributes(Attributes attributes) throws NamingException {
				User user = new User();
				if (attributes != null) {
					Attribute idAttr = attributes.get("uid");
					if (idAttr != null) {
						user.setId((String) idAttr.get(1));
					}
					Attribute nameAttr = attributes.get("sn");
					if (nameAttr != null) {
						user.setName((String) nameAttr.get(1));
					}
				}
				return user;
			}

		});
	}

}
