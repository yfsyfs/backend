package com.yfs.bean;

public class Person {

	private String nickname;
	private Integer age;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [nickname=" + nickname + ", age=" + age + "]";
	}

	public Person() {
		System.out.println("我不会被调用的...");
	}
}
