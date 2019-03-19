package com.hikvision.nanchang.doorguardblacklist.mapper.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
	@Insert("insert into \"TB_DEVICE\"(\"ID\", \"RESOURCE_ID\", \"IS_BLACKLIST_ON\", \"INDEXCODE\", \"cn\", \"device_type\", \"device_model\",\"ip\", \"port\",\"password_strength\") VALUES(#{id}, #{resourceId}, 'on', 0, #{indexcode}, #{cn}), #{deviceType}, #{deviceModel},#{ip},#{port},#{pwdStrength}")
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
	* @Description: 查找所有数据库中处于开启黑名单功能状态的设备, 按照设备名称升序
	* @param @return
	* @return List<Device> 
	* @throws
	 */
	@Select("SELECT \"ID\" as id, \"RESOURCE_ID\" as resourceId, \"IS_BLACKLIST_ON\" as status, \"cn\", \"device_model\" as deviceModel, \"device_type\" as deviceType, \"ip\", \"port\", \"INDEXCODE\" as indexcode,\"password_strength\" as pwdStrength FROM \"TB_DEVICE\" WHERE \"IS_BLACKLIST_ON\"='on' AND \"cn\" LIKE CONCAT('%',#{cn}, '%') order by \"cn\"")
	List<Device> findAllOnAsc(@Param("cn") String cn);

	/**
	* @Description: 查找所有数据库中处于开启黑名单功能状态的设备, 按照设备名称降序
	* @param @return
	* @return List<Device> 
	* @throws
	 */
	@Select("SELECT \"ID\" as id, \"RESOURCE_ID\" as resourceId, \"IS_BLACKLIST_ON\" as status, \"cn\", \"device_model\" as deviceModel, \"device_type\" as deviceType, \"ip\", \"port\", \"INDEXCODE\" as indexcode,\"password_strength\" as pwdStrength FROM \"TB_DEVICE\" WHERE \"IS_BLACKLIST_ON\"='on' AND \"cn\" LIKE CONCAT('%',#{cn}, '%') order by \"cn\" desc")
	List<Device> findAllOnDesc(@Param("cn") String cn);

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
