package com.hikvision.nanchang.doorguardblacklist.mapper.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.provider.BlacklistEventDynamicSqlProvider;
import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 黑名单事件mapper 
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public interface BlacklistEventMapper {

	/**
	* @Description: 条件查询事件   
	* @param @param params
	* @param @return
	* @return List<BlacklistEvent> 
	* @throws
	 */
	@SelectProvider(type = BlacklistEventDynamicSqlProvider.class, method = "selectWithParams")
	List<BlacklistEvent> queryByConditions(Map<String, Object> params);

	/**
	* @Description: 插入黑名单事件   
	* @param @param blacklist
	* @param @return 成功插入的条数
	* @return Integer 
	* @throws
	 */
	@InsertProvider(type = BlacklistEventDynamicSqlProvider.class, method = "insertWithParams")
	Integer insert(BlacklistEvent blacklist);
	
}
