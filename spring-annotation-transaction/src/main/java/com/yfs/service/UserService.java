package com.yfs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yfs.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Transactional // 告诉Spring当前方法是一个事务方法, 则Spring在执行这个方法的时候就会进行事务控制,
					// 如果整个方法都正常执行的话，所有的操作都生效提交，反之则所有操作都回滚.
	public void insert() {
		userDao.insert();
		int i = 1 / 0;
	}

}
