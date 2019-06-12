package com.yfs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfs.dao.BookDao;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
}
