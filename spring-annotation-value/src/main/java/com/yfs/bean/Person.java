package com.yfs.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

	// 常量
	@Value("影法师")
	private String name;
	// SpEL
	@Value("#{20-2}")
	private Integer age;

	// 配置文件
	@Value("${person.nickname}")
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", nickname=" + nickname + "]";
	}

}
