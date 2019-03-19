package com.hikvision.nanchang.doorguardblacklist.service;

import java.util.List;
import java.util.Map;

import com.hikvision.nanchang.doorguardblacklist.common.Page;
import com.hikvision.nanchang.doorguardblacklist.po.Device;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 设备服务接口（数据来源ldap服务）
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public interface DeviceLdapService {

	/**
	* @Description: 根据设备编码查询设备   
	* @param @param params
	* @param @return
	* @return List<Device> 
	* @throws
	 */
	Device queryByIndexcode(String indexcode);

	/**
	* @Description: 根据设备resourceId 查询设备   
	* @param @param resourceId
	* @param @return
	* @return Device 
	* @throws
	 */
	Device queryByResourceId(String resourceId);

	/**
	* @Description: 条件分页查询支持黑名单功能设备列表（其实现在只是根据设备名称模糊查询）   
	* @param @param devicename
	* @param @return
	* @return List<Device> 
	* @throws
	 */
	Page<Device> queryBlacklistDeviceByConditions(Map<String, Object> params) throws Exception;

	/**
	* @Description: 根据设备名称模糊查询全部设备（包括开启以及未开启黑名单设备），而且不分页, 仅仅依据设备名称进行模糊查询
	* @param @return
	* @return List<Device> 
	* @throws
	 */
	List<Device> queryDeviceByConditions(String name);

	/**
	* @Description: 批量开启设备的黑名单功能   
	* @param @param indexcodes 设备编码列表
	* @param @return
	* @return boolean 
	* @throws
	 */
	boolean add(String[] resourceIds);

	/**
	* @Description: 批量关闭设备的黑名单功能   
	* @param @param indexcodes
	* @param @return
	* @return boolean 
	* @throws
	 */
	boolean remove(String[] resourceIds);

}
