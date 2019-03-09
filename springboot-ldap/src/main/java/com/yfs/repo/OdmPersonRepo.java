package com.yfs.repo;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.LinkedList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.LdapOperationsCallback;
import org.springframework.ldap.core.support.SingleContextSource;
import org.springframework.ldap.filter.PresentFilter;
import org.springframework.stereotype.Repository;

import com.yfs.po.Person;

@Repository
public class OdmPersonRepo {

	@Autowired
	private LdapTemplate ldapTemplate;

	/**
	* @Description: 增 
	* @author: 影法师
	* @date: 2019年3月7日 下午10:13:14
	* @param: 
	* @return: void
	* @throws
	 */
	public Person create(Person person) {
		ldapTemplate.create(person);
		return person;
	}
	
	public Person findByUid(String uid) {
		return ldapTemplate.findOne(query().where("cn").is("xie"), Person.class);
	}

	public List<Person> findAll() {
		// 注意，感觉spring-ldap有个bug, 
		// 在 org.springframework.ldap.core.LdapTemplate.find(Name, Filter, SearchControls, Class<T>) 中的1846行. 
		// 它只移除了一个null. 万一多个null的话. 则这里返回的列表中就会有null 
		return ldapTemplate.findAll(Person.class);
	}

	public List<Person> findByLastName(String lastName) {
		return ldapTemplate.search("o=xxx,ou=study", new PresentFilter("ou").encode(), new AbstractContextMapper<Person>() {
			@Override
			protected Person doMapFromContext(DirContextOperations ctx) {
				Person person = new Person();
//				person.setOuu(ctx.getStringAttribute("ou"));
				person.setCommonName(ctx.getStringAttribute("cn"));
				person.setSuerName(ctx.getStringAttribute("sn"));
				return person;
			}
		});
//		return ldapTemplate.find(query().where("sn").isPresent(), Person.class);
	}
	
	/**
	* @Description: 分页查询 
	* @author: 影法师
	* @date: 2019年3月8日 下午12:16:45
	* @param: @param cur
	* @param: @param size
	* @return: void
	* @throws
	 */
	public List<String> searhByPage(int cur, int size) {
		final SearchControls searchControls = new SearchControls();
		// 子树都搜索
		  searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		  final PagedResultsDirContextProcessor processor =
		        new PagedResultsDirContextProcessor(size);
		  //
		  return SingleContextSource.doWithSingleContext(
		        ldapTemplate.getContextSource(), new LdapOperationsCallback<List<String>>() {
		      @Override
		      public List<String> doWithLdapOperations(LdapOperations operations) {
		        List<String> result = new LinkedList<String>();
		        int count = 0;
		        do {
		          result = operations.search(
		            "ou=study",
		            "(&(objectclass=inetOrgPerson))",
		            searchControls,
		            new AttributesMapper<String>() {
		            	public String mapFromAttributes(javax.naming.directory.Attributes attributes) throws javax.naming.NamingException {
		            		return (String) attributes.get("sn").get();
		            	};
					},
		            processor);
		          count++;
		          if (count == cur) {
					break;
				}
		        } while(processor.hasMore());

		        return result;
		      }
		  });
	}

}
