package com.hikvision.nanchang.doorguardblacklist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.SysconfigMapper;
import com.hikvision.nanchang.doorguardblacklist.po.SysConfig;
import com.hikvision.nanchang.doorguardblacklist.service.SysconfigService;

@Service
public class SysconfigServiceImpl implements SysconfigService {

	@Autowired
	private SysconfigMapper sysconfigMapper;

	@CacheEvict(value = "sysconfig", allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean update(String paramValue, String paramName) {
		// 一定先查询配置项ID，防止高并发下数据库死锁
		sysconfigMapper.update(paramValue, query(paramName).getParamConfigId());
		return true;
	}

	@Cacheable("sysconfig")
	@Override
	public SysConfig query(String paramName) {
		return sysconfigMapper.query(paramName);
	}

}
