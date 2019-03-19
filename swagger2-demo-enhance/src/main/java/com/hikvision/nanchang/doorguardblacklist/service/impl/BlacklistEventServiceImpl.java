package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.BlacklistEventMapper;
import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistEventService;

@Service
public class BlacklistEventServiceImpl implements BlacklistEventService {

	@Autowired
	private BlacklistEventMapper blacklistEventMapper;

	@Cacheable("blacklistEvent")
	@Override
	public List<BlacklistEvent> queryByConditions(Map<String, Object> params) {
		return blacklistEventMapper.queryByConditions(params);
	}

	@CacheEvict(value = "blacklistEvent", allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer insert(BlacklistEvent blacklist) {
		return blacklistEventMapper.insert(blacklist);
	}

}
