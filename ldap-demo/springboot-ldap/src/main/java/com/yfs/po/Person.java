package com.yfs.po;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Entry(base = "ou=Demo,dc=app1,dc=yfs,dc=com", objectClasses = "inetOrgPerson")
@Data
public class Person {

	@Id
	@JsonIgnore // @JsonIgnore是为了将person传给前端时不报错，因为Name类型的无法自动解析成json格式。
	private Name dn;
	// index属性是 The index of this attribute in the calculated distinguished name of an entry. 返回 the 0-based（即index是从0开始计数的） index of this attribute.
	@DnAttribute(value = "uid")
	private String uid;
	@Attribute(name = "cn")
	private String commonName;
	@Attribute(name = "sn")
	private String suerName;
	// 不写的话, 默认就是映射ldif文件中的 userPassword 属性
	private String userPassword;

}
