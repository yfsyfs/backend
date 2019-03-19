package com.hikvision.nanchang.doorguardblacklist.service;

import java.util.List;
import java.util.Map;

import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 黑名单事件服务接口 
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public interface BlacklistEventService {

	/**
	* @Description: 条件分页查询   
	* @param @param params
	* @param @return
	* @return List<BlacklistEvent> 
	* @throws
	 */
	List<BlacklistEvent> queryByConditions(Map<String, Object> params);

	/**
	* @Description: 插入黑名单事件   
	* @param @param blacklist
	* @param @return
	* @return Integer 
	* @throws
	 */
	Integer insert(BlacklistEvent blacklist);

}
