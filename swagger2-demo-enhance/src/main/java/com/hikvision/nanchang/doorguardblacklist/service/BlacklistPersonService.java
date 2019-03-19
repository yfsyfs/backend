package com.hikvision.nanchang.doorguardblacklist.service;

import java.util.List;
import java.util.Map;

import com.hikvision.nanchang.doorguardblacklist.po.BlackListPerson;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 黑名单人员服务接口
* @author xiexin6  
* @date 2019年3月11日  
*
 */
public interface BlacklistPersonService {

	/**
	* @Description: 条件查询黑名单人员列表   
	* @param @param params
	* @param @return
	* @return List<BlackListPerson> 
	* @throws
	 */
	List<BlackListPerson> queryByConditions(Map<String, Object> params);

	/**
	* @Description: 插入黑名单人员   
	* @param @param blackListPerson
	* @param @return
	* @return int 
	* @throws
	 */
	int insert(BlackListPerson blackListPerson);

	/**
	* @Description: 更新黑名单人员信息   
	* @param @param blackListPerson
	* @param @return
	* @return boolean 
	* @throws
	 */
	boolean update(BlackListPerson blackListPerson);

	/**
	* @Description: 删除永久黑名单人员信息   
	* @param @param ids 永久黑名单人员身份证号
	* @param @return
	* @return boolean 
	* @throws
	 */
	boolean remove(String[] ids);

	/**
	* @Description: 下发黑名单到门禁组件   
	* @param 
	* @return void 
	* @throws
	 */
	void dispatch();

}
