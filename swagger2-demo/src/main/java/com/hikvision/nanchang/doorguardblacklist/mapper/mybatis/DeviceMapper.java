package com.hikvision.nanchang.doorguardblacklist.mapper.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hikvision.nanchang.doorguardblacklist.po.Device;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 设备mapper 
* @author xiexin6  
* @date 2019年3月8日  
*
 */
public interface DeviceMapper {
	/**
	* @Description: 查询设备的黑名单开启状态   
	* @param @param resourceId 设备的resourceId
	* @param @return
	* @return String on表示开启, off 表示未开启
	* @throws
	 */
	@Select("select \"IS_BLACKLIST_ON\" from \"TB_DEVICE\" WHERE \"RESOURCE_ID\"=#{value}")
	String isBlacklistOn(String resourceId);

	/**
	* @Description: 添加黑名单设备   
	* @param @param device
	* @param @return
	* @return Integer 
	* @throws
	 */
	@Insert("insert into \"TB_DEVICE\"(\"ID\", \"RESOURCE_ID\", \"IS_BLACKLIST_ON\", \"THRESHOLD\", \"INDEXCODE\") VALUES(#{id}, #{resourceId}, 'on', 0, #{indexcode})")
	Integer add(Device device);

	/**
	* @Description: 根据resourceId查询设备   
	* @param @param resourceId
	* @param @return
	* @return Device 
	* @throws
	 */
	@Select("select \"ID\" as id, \"RESOURCE_ID\" as resourceId, \"IS_BLACKLIST_ON\" as status from \"TB_DEVICE\" where \"RESOURCE_ID\"=#{value}")
	Device findOneByResourceId(String resourceId);

	/**
	* @Description: 开启设备黑名单功能   
	* @param @param resourceId 设备资源id
	* @return void 
	* @throws
	 */
	@Update("update \"TB_DEVICE\" SET \"IS_BLACKLIST_ON\"='on' where \"RESOURCE_ID\"=#{value}")
	void on(String resourceId);

	/**
	* @Description: 关闭设备黑名单功能   
	* @param @param resourceId 设备资源id
	* @return void 
	* @throws
	 */
	@Update("update \"TB_DEVICE\" SET \"IS_BLACKLIST_ON\"='off' where \"RESOURCE_ID\"=#{value}")
	void off(String resourceId);

	/**
	* @Description: 更新设备黑名单报警阈值
	* @param @param device
	* @param @return
	* @return Integer 
	* @throws
	 */
	@Update("update \"TB_DEVICE\" SET \"THRESHOLD\"=#{threshold} where \"RESOURCE_ID\"=#{resourceId}")
	Integer updateThreshold(Device device);

	/**
	* @Description: 根据 indexCode（设备编码）查询黑名单设备的报警阈值      
	* @param @param indexcode
	* @param @return
	* @return Integer 
	* @throws
	 */
	@Select("select \"THRESHOLD\" from \"TB_DEVICE\" where \"INDEXCODE\"=#{value}")
	Integer queryThresholdByIndexcode(String indexcode);

}
