package com.yfs.po;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

@Entry(base = "o=xxx,ou=study", objectClasses = { "inetOrgPerson" })
public class Person {

	@Id
	//	@JsonIgnore // @JsonIgnore是为了将person传给前端时不报错，因为Name类型的无法自动解析成json格式。但是这里没导入json的包
	private Name dn;
	@DnAttribute(value = "cn", index = 2)
	//	@Attribute(name = "cn")
	@Transient
	private String ouu;

	@DnAttribute(value = "cn", index = 1)
		@Attribute(name = "cn")
//	@Transient
	private String ouu2;
	@DnAttribute(value = "ou", index = 0)
//	@Attribute(name = "ou")
		@Transient  // 注意，这是spring ldap 包下的Transitent, 不要写错了!!!不然看不到效果的
	private String ouu1;
	@Attribute(name = "cn")
	private String commonName;
	@Attribute(name = "sn")
	private String suerName;

	public String getOuu2() {
		return ouu2;
	}

	public void setOuu2(String ouu2) {
		this.ouu2 = ouu2;
	}

	@Override
	public String toString() {
		return "Person [ouu=" + ouu + ", commonName=" + commonName + ", suerName=" + suerName + "]";
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getOuu() {
		return ouu;
	}

	public void setOuu(String ouu) {
		this.ouu = ouu;
	}

	public String getOuu1() {
		return ouu1;
	}

	public void setOuu1(String ouu1) {
		this.ouu1 = ouu1;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getSuerName() {
		return suerName;
	}

	public void setSuerName(String suerName) {
		this.suerName = suerName;
	}

}
