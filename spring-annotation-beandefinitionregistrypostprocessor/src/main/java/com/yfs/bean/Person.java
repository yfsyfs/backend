package com.yfs.bean;

public class Person {

	private String name;
	private Integer age;

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
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public Person(String name, Integer age) {
		System.out.println("我会被调用的~");
		this.name = name;
		this.age = age;
	}

	public Person() {
		System.out.println("我不会被调用的...");
	}
}
