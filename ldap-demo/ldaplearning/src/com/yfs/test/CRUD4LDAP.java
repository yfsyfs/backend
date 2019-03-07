package com.yfs.test;

import java.util.Hashtable;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CRUD4LDAP {

	private DirContext context = null;
	private static final String BASE_DN = "dc=yfs,dc=com";
	public static final String LDAP_URL = "ldap://localhost:389/" + BASE_DN; // 不写 389也行, 389是ldap的默认TCP端口， 注意，其实这个和URL非常像, 如果你将LDAP中的dn看成是目录的话
	private static final String USERNAME = "cn=Manager,dc=yfs,dc=com";
	private static final String PASSWORD = "secret";
	private static final String INITIAL_CONTEXTFACTORY_NAME = "com.sun.jndi.ldap.LdapCtxFactory";

	/**
	* @Description: 打开ldap连接 
	* @author: 影法师
	* @date: 2019年3月5日 下午9:49:50
	* @param: 
	* @return: void
	* @throws
	 */
	@Before
	public void init() {
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXTFACTORY_NAME);
		env.put(Context.PROVIDER_URL, LDAP_URL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, USERNAME);
		env.put(Context.SECURITY_CREDENTIALS, PASSWORD);

		try {
			context = new InitialDirContext(env);
			System.out.println("LDAP认证成功!");
		} catch (NamingException e) {
			System.out.println("LDAP认证异常...");
			e.printStackTrace();
		}

	}

	/**
	* @Description: 新建一个组织单元 
	* @author: 影法师
	* @date: 2019年3月5日 下午8:45:24
	* @param: @param newUsername
	* @return: void
	* @throws
	 */
	@Test
	public void add() {
		String newUsername = "法师组织南昌分舵";
		// 条目的属性们(LDAP中的条目就是属性的集合，而每个属性的结构是<键, 若干个值>)
		BasicAttributes attributes = new BasicAttributes();
		BasicAttribute objclassSet = new BasicAttribute("objectclass");
		// 注意objectclass 不是随便定义的(不然报错), 即便是扩展 也需要自定义scheme文件， 详见 LDAP 自定义 objectclass 和属性.pdf
		objclassSet.add("inetOrgPerson");
		attributes.put(objclassSet);
		attributes.put("ou", newUsername); // 这个不加也会出现在此条目的属性中的
		attributes.put("sn", "影法师");
		attributes.put("cn", "影法");
		try {
			// 注意, 因为context中已经有了BASE DN, 则 这里就不需要带了. 不然报错的 详见 LDAP error code 32  No Such Object.pdf
			// 其实本质原因是LDAP_URL，如果LDAP_URL去掉了BASE_DN, 则下面都要带上BASE_DN
			// 譬如下面的第一个入参就要改成 "ou="+newUsername+","+BASE_DN, delete方法中也要改成
			// dn="ou=法师组织南昌分舵,"+BASE_DN 了
			// 注意, 默认会添加1个 ou=第一个入参的属性到此条目中的, 如果上面也attributes.put("ou", "xxx"), 则此条目就会有
			// 2个ou的值, 一个值=xxx，一个值等于这里的入参. 如果两者一样, 则只会出现一个ou=...
			context.createSubcontext("ou=3" + newUsername, attributes);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	/**
	* @Description: 删 
	* @author: 影法师
	* @date: 2019年3月5日 下午9:20:39
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void delete() {
		String dn = "ou=3法师组织南昌分舵";
		try {
			context.destroySubcontext(dn);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	* @Description: 重命名 ou=3法师组织南昌分舵,dc=yfs,dc=com 的条目为ou=3法师组织北京分舵,dc=yfs,dc=com的条目, 注意, 同上，因为LDAP_URL加了BASE_DN, 所以这里不需要再加BASE_DN了 不然报错的, 注意，不能重命名非叶子节点, 不然也报错,只支持叶子节点的重命名, 其实想想也是, 如果非叶子节点支持的话, 则叶子节点也要递归的改
	* @author: 影法师
	* @date: 2019年3月5日 下午9:36:07
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void rename() {
		String oldName = "ou=3法师组织南昌分舵";
		String newName = "ou=3法师组织北京分舵";
		try {
			context.rename(oldName, newName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	* @Description: 为dc=app2,dc=yfs,dc=com这个条目添加一个属性  o=xxx1  注意, 不能重复添加键和值一样的键值对, 也就是添加 <o,x>和<o,y>是可以的, 但是不能两次添加的都是<o,x> 不然报错
	* @author: 影法师
	* @date: 2019年3月5日 下午9:56:00
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void update_add_attribute() {
		// 因为LDAP_URL 带了BASE_DN, 所以这里不需要带BASE_DN
		String dn = "dc=app2";
		String value = "xxx1";
		ModificationItem[] modificationItems = new ModificationItem[1];
		// 注意，这个属性也不能随便写, 也是要在scheme中有定义的. 详见 LDAP 自定义 objectclass 和属性.pdf
		Attribute attribute = new BasicAttribute("o", value);
		modificationItems[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, attribute);
		try {
			context.modifyAttributes(dn, modificationItems);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	* @Description: 为dc=app2,dc=yfs,dc=com这个条目删除一个属性  o=xxx
	* @author: 影法师
	* @date: 2019年3月5日 下午9:56:00
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void update_delete_attribute() {
		// 因为LDAP_URL 带了BASE_DN, 所以这里不需要带BASE_DN
		String dn = "dc=app2";
		String value = "xxx";
		ModificationItem[] modificationItems = new ModificationItem[1];
		// 注意，这个属性也不能随便写, 也是要在scheme中有定义的. 详见 LDAP 自定义 objectclass 和属性.pdf
		Attribute attribute = new BasicAttribute("o", value);
		modificationItems[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attribute);
		try {
			context.modifyAttributes(dn, modificationItems);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	* @Description: 为dc=app2,dc=yfs,dc=com这个条目修改一个属性名为o的所有属性值为xxx，也就时最后以o为属性名的属性键值对只剩下<o,xxx>
	* @author: 影法师
	* @date: 2019年3月5日 下午9:56:00
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void update_modify_attribute() {
		// 因为LDAP_URL 带了BASE_DN, 所以这里不需要带BASE_DN
		String dn = "dc=app2";
		String value = "xxx";
		ModificationItem[] modificationItems = new ModificationItem[1];
		// 注意，这个属性也不能随便写, 也是要在scheme中有定义的. 详见 LDAP 自定义 objectclass 和属性.pdf
		Attribute attribute = new BasicAttribute("o", value);
		modificationItems[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute);
		try {
			context.modifyAttributes(dn, modificationItems);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	* @Description: 根据过滤条件查 某个节点(这里是 根节点dc=yfs,dc=com)下面的所有符合过滤条件(objectClass为organizationalUnit)的节点(即条目), 并且遍历打印条目的属性
	* @author: 影法师
	* @date: 2019年3月5日 下午10:15:33
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void query1() {
		// 因为LDAP_URL带了BASE_DN 所以这里不必带.
		String base = "";
		String scope = "";
		String filter = "objectClass=organizationalUnit";
		SearchControls sc = new SearchControls();
		// 搜索范围,分为"base"(本节点),"one"(单层),""(遍历)
		if (scope.equals("base")) {
			sc.setSearchScope(SearchControls.OBJECT_SCOPE);
		} else if (scope.equals("one")) {
			sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		} else {
			sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
		}
		NamingEnumeration ne = null;
		try {
			ne = context.search(base, filter, sc);
			while (ne.hasMore()) {
				System.out.println();
				SearchResult sr = (SearchResult) ne.next();
				String name = sr.getName();
				if (base != null) {
					System.out.println("entry: " + name + "," + base);
				} else {
					System.out.println("entry: " + name);
				}
				Attributes at = sr.getAttributes();
				NamingEnumeration ane = at.getAll();
				while (ane.hasMore()) {
					Attribute attr = (Attribute) ane.next();
					String attrType = attr.getID();
					NamingEnumeration values = attr.getAll();
					while (values.hasMore()) {
						Object oneVal = values.nextElement();
						if (oneVal instanceof String) {
							System.out.println(attrType + ": " + (String) oneVal);
						} else {
							System.out.println(attrType + ": " + new String((byte[]) oneVal));
						}
					}
				}
			}
		} catch (Exception nex) {
			System.err.println("Error: " + nex.getMessage());
			nex.printStackTrace();
		}

	}

	/**
	* @Description: 查询特定条目（entry） 
	* @author: 影法师
	* @date: 2019年3月5日 下午10:26:27
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void query2() {
		String value = "organizationalUnit";
		// Create the search controls
		SearchControls searchCtls = new SearchControls();
		// Specify the search scope
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		// specify the LDAP search filter
		String searchFilter = "objectClass=" + value;
		// Specify the Base for the search 搜索域节点
		String searchBase = "";
		int totalResults = 0;
		String returnedAtts[] = { "url", "whenChanged", "employeeID", "name", "userPrincipalName",
				"physicalDeliveryOfficeName", "departmentNumber", "telephoneNumber", "homePhone", "mobile",
				"department", "sAMAccountName", "whenChanged", "mail" }; // 定制返回属性

		searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集

		// searchCtls.setReturningAttributes(null); // 不定制属性，将返回所有的属性集

		try {
			NamingEnumeration answer = context.search(searchBase, searchFilter, searchCtls);
			if (answer == null || answer.equals(null)) {
				System.out.println("answer is null");
			} else {
				System.out.println("answer not null");
			}
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				System.out.println("************************************************");
				System.out.println("getname=" + sr.getName());
				Attributes Attrs = sr.getAttributes();
				if (Attrs != null) {
					try {

						for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
							Attribute Attr = (Attribute) ne.next();
							System.out.println("AttributeID=" + Attr.getID().toString());
							// 读取属性值
							for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {
								String user = e.next().toString(); // 接受循环遍历读取的userPrincipalName用户属性
								System.out.println(user);
							}
							// System.out.println(" ---------------");
							// // 读取属性值
							// Enumeration values = Attr.getAll();
							// if (values != null) { // 迭代
							// while (values.hasMoreElements()) {
							// System.out.println(" 2AttributeValues="
							// + values.nextElement());
							// }
							// }
							// System.out.println(" ---------------");
						}
					} catch (NamingException e) {
						System.err.println("Throw Exception : " + e);
					}
				}
			}
			System.out.println("Number: " + totalResults);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Throw Exception : " + e);
		}

	}

	/**
	* @Description: 关闭ldap连接
	* @author: 影法师
	* @date: 2019年3月5日 下午9:49:43
	* @param: 
	* @return: void
	* @throws
	 */
	@After
	public void close() {
		if (context != null) {
			try {
				context.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

}
