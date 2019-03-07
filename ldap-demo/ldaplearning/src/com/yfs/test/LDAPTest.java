package com.yfs.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.junit.Test;

/**
* @Description: 测试java客户端能否正常连接ldap服务器 
* @author: 影法师
* @date: 2019年3月5日 下午5:02:46
*
* @Copyright: 2019 https://yfsyfs.github.io Inc. All rights reserved.
* 注意：本内容仅限于影法师信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class LDAPTest {

	/**
	* @Description: 测试连接ldap服务器 
	* @author: 影法师
	* @date: 2019年3月5日 下午5:09:53
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void connect() {
		String root = "dc=yfs,dc=com"; //base dn 

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost/" + root);
		env.put(Context.SECURITY_AUTHENTICATION, "simple"); // 安装的时候选择了简单认证
		env.put(Context.SECURITY_PRINCIPAL, "cn=Manager,dc=yfs,dc=com"); // 
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
			System.out.println("认证成功");
		} catch (javax.naming.AuthenticationException e) {
			e.printStackTrace();
			System.out.println("认证失败");
		} catch (Exception e) {
			System.out.println("认证出错：");
			e.printStackTrace();
		}
		SearchControls searchControls = new SearchControls();
		// Search the entire subtree rooted at the named object
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		try {
			NamingEnumeration<SearchResult> searchResult = ctx.search("", "uid=Miumiu", searchControls);
			if (searchResult == null || !searchResult.hasMoreElements()) {
				System.out.println("Miumiu用户不存在!");
				return;
			}
			while (searchResult.hasMoreElements()) {
				SearchResult next = searchResult.next();
				// next的内容是uid=Miumiu,ou=Tester: null:null:{labeleduri=labeledURI: http://www.yfs.com, userpassword=userPassword: [B@c46bcd4, uid=uid: Miumiu, objectclass=objectClass: inetOrgPerson, sn=sn: Wu, cn=cn: Miumiu Wu}
				// next.getName 是 uid=Miumiu,ou=Tester
				System.out.println(next.getName());
				Attributes attributes = next.getAttributes();
				NamingEnumeration<String> iDs = attributes.getIDs();
				/*
				 	labeledURI
					userPassword
					uid
					objectClass
					sn
					cn	
				 */
				while (iDs.hasMoreElements()) {
					System.out.println(iDs.next());
				}
				System.out.println("---------------------------------");
				// labeledURI: http://www.yfs.com
				System.out.println(attributes.get("labeledURI"));
				System.out.println("------------------------------------");
				/*
				都是属性值（一个属性有一个属性名，若干个属性值）
				http://www.yfs.com
				****************************************
				[B@1698c449
				****************************************
				Miumiu
				****************************************
				inetOrgPerson
				****************************************
				Wu
				****************************************
				Miumiu Wu
				****************************************
				 */
				NamingEnumeration<? extends Attribute> allAttributes = attributes.getAll();
				while (allAttributes.hasMoreElements()) {
					NamingEnumeration<?> all = allAttributes.next().getAll();
					while (all.hasMoreElements()) {
						System.out.println(all.next());
					}
					System.out.println("****************************************");
				}
			}
			// 添加用户(不能重复添加, 不然报错)
			BasicAttributes attrsbu = new BasicAttributes();
			BasicAttribute objclassSet = new BasicAttribute("objectclass");
			objclassSet.add("inetOrgPerson");
			attrsbu.put(objclassSet);
			attrsbu.put("sn", "影");
			attrsbu.put("cn", "影法师");
			attrsbu.put("uid", "666");
			attrsbu.put("userPassword", "123456");
			attrsbu.put("description", "影法师的绝技");
			ctx.createSubcontext("uid=" + 666 + "", attrsbu);
		} catch (NamingException e1) {
			e1.printStackTrace();
		}

		if (ctx != null) {
			try {
				ctx.close();
			} catch (NamingException e) {
				//ignore     
			}
		}
		System.exit(0);
	}

}
