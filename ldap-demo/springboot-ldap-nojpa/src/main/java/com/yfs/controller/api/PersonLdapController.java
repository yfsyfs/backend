package com.yfs.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yfs.entry.Person;
import com.yfs.service.PersonLdapService;

@RestController
public class PersonLdapController {

	@Autowired
	private PersonLdapService personLdapService;

	@RequestMapping(value = "/findOne", method = RequestMethod.POST)
	public Person findByCn(@RequestParam(name = "cn", required = true) String cn) {
		Person person = personLdapService.findByCn(cn);
		System.out.println(person);
		return person;
	}

	@PostMapping(value = "/create")
	public Person create(@RequestParam(name = "cn") String cn, @RequestParam(name = "sn") String sn,
			@RequestParam(name = "userPassword") String userPassworld) {
		Person person = new Person();
		person.setCn(cn);
		person.setSn(sn);
		person.setUserPassword(userPassworld);
		return personLdapService.create(person);
	}

	@PostMapping(value = "/update")
	public Person update(@RequestParam(name = "cn") String cn, @RequestParam(name = "sn") String sn,
			@RequestParam(name = "userPassword") String userPassworld) {
		Person person = new Person();
		person.setCn(cn);
		person.setSn(sn);
		person.setUserPassword(userPassworld);
		return personLdapService.modifyPerson(person);
	}

	@PostMapping(value = "/delete")
	public void delete(@RequestParam(name = "cn") String cn) {
		Person person = new Person();
		person.setCn(cn);
		personLdapService.deletePerson(person);
	}
}
