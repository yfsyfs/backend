package com.hikvision.nanchang.doorguardblacklist.service;

import com.hikvision.nanchang.doorguardblacklist.po.SysConfig;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 系统配置服务
* @author xiexin6  
* @date 2019年3月13日  
*
 */
public interface SysconfigService {

	/**
	* @Description: 根据配置项的名称更新设备报警阈值   
	* @param @param num
	* @param @return
	* @return boolean 
	* @throws
	 */
	boolean update(String paramValue, String paramName);

	/**
	* @Description: 根据配置项的名称查询配置项   
	* @param @param paramName
	* @param @return
	* @return SysConfig 
	* @throws
	 */
	SysConfig query(String paramName);

}
