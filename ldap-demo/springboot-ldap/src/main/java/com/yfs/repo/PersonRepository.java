package com.yfs.repo;

import org.springframework.data.repository.CrudRepository;

import com.yfs.po.Person;

import javax.naming.Name;

public interface PersonRepository extends CrudRepository<Person, Name> {

}