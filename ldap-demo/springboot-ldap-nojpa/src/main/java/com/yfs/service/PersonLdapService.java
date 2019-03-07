package com.yfs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import com.yfs.entry.Person;

@Service
public class PersonLdapService {
	@Autowired
	private LdapTemplate ldapTemplate;

	public Person create(Person person) {
		ldapTemplate.create(person);
		return person;
	}

	public Person findByCn(String cn) {
		return ldapTemplate.findOne(query().where("cn").is(cn), Person.class);
	}

	public Person modifyPerson(Person person) {
		ldapTemplate.update(person);
		return person;
	}

	public void deletePerson(Person person) {
		ldapTemplate.delete(person);
	}
}
