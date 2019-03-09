package com.yfs;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yfs.po.Person;
import com.yfs.repo.OdmPersonRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private OdmPersonRepo personRepo;

	@Test
	public void test1() {
		Person person = new Person();
		person.setCommonName("xin");
		person.setSuerName("xin");
		person.setOuu("study6");
		person.setOuu1("vvv");
		person.setOuu2("cfs");
		personRepo.create(person);
	}
	
	@Test
	public void test2() {
		List<Person> persons = personRepo.findAll();
		for (Person person : persons) {
			System.out.println(person);
		}
	}
	
	@Test
	public void test3() {
		List<Person> persons = personRepo.findByLastName("xie");
		for (Person person : persons) {
			System.out.println(person);
		}
	}
	
	@Test
	public void test4() {
		List<String> persons = personRepo.searhByPage(100, 2);
		for (String string : persons) {
			System.out.println(string);
		}
	}
}
