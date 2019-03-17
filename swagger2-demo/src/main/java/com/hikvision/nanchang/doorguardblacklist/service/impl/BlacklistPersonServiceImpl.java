package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.BlacklistPersonMapper;
import com.hikvision.nanchang.doorguardblacklist.po.BlackListPerson;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistPersonService;

@Service
public class BlacklistPersonServiceImpl implements BlacklistPersonService {

	@Autowired
	private BlacklistPersonMapper blacklistPersonMapper;

	@Cacheable("blacklistPeople")
	@Override
	public List<BlackListPerson> queryByConditions(Map<String, Object> params) {
		return blacklistPersonMapper.queryByConditions(params);
	}

	@CacheEvict(value = "blacklistPeople", allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int insert(BlackListPerson blackListPerson) {
		return blacklistPersonMapper.insert(blackListPerson);
	}

	@CacheEvict(value = "blacklistPeople", allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean update(BlackListPerson blackListPerson) {
		return blacklistPersonMapper.update(blackListPerson);
	}

	@CacheEvict(value = "blacklistPeople", allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean remove(String[] ids) {
		if (ids == null) {
			blacklistPersonMapper.clean();
			return true;
		}
		for (String id : ids) {
			blacklistPersonMapper.remove(id);
		}
		return true;
	}

	@Override
	public void dispatch() {
		// TODO 调用门禁组件的接口
	}

}
