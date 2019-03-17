package com.hikvision.nanchang.doorguardblacklist.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hikvision.nanchang.doorguardblacklist.service.BlacklistPersonService;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 次日凌晨零点清空黑名单
* @author xiexin6  
* @date 2019年3月16日  
*
 */
@Component
public class CleanTmpBlacklistPersonJob {

	@Autowired
	private BlacklistPersonService blacklistPersonService;

	@CacheEvict(value = "blacklistPeople", allEntries = true)
	@Scheduled(cron = "0 0 0 * * ?")
	public void clean() {
		blacklistPersonService.remove(null);
	}

}
