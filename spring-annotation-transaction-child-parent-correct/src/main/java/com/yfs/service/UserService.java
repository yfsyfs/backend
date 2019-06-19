package com.yfs.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yfs.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 最终效果是，insert_parent插入成功，insert_child插入失败,
	 * 如果将insert_parent中的try...catch去掉，则insert_parent和insert_child都会失败(因为insert_parent捕获到了异常，要回滚)
	 * 
	 * 日志中有两条
	 * 
	 * DEBUG - Creating new transaction with name [com.yfs.service.UserService.insert_parent]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
	 * DEBUG - Suspending current transaction, creating new transaction with name [com.yfs.service.UserService.insert_child]
	 * 
	 * 可见，insert_child上的事务奏效了
	 */
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert_parent() {
		System.out.println(this.getClass().getName()); // com.yfs.service.UserService 注意，不会打印出代理类，而是被代理对象
		userDao.insert("parent");
		try {
			((UserService) AopContext.currentProxy()).insert_child(); // 通过AopContext从当前线程获取代理对象调用事务方法就会走事务代理,
																		// 则此时insert_child上的@Transactional注解就生效了
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insert_child() {
		userDao.insert("child");
		int i = 1 / 0;
	}

}
