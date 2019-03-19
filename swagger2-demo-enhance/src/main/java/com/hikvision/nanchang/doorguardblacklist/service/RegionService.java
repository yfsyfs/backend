package com.hikvision.nanchang.doorguardblacklist.service;

import java.util.List;

import com.hikvision.nanchang.doorguardblacklist.treevo.RegionTreeVO;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 区域服务 
* @author xiexin6  
* @date 2019年3月18日  
*
 */
public interface RegionService {

	/**
	* @Description: 根据regionId和cn查询此区域下面的所有节点   
	* @param @param regionId
	* @param @return
	* @return List<RegionTreeVO> 
	* @throws
	 */
	List<RegionTreeVO> query(String regionId, String cn);

}
