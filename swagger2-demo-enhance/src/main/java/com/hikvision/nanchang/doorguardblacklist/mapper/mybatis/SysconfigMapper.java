package com.hikvision.nanchang.doorguardblacklist.mapper.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hikvision.nanchang.doorguardblacklist.po.SysConfig;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 系统配置mapper 
* @author xiexin6  
* @date 2019年3月13日  
*
 */
public interface SysconfigMapper {

	/**
	* @Description: 更新设备报警阈值   
	* @param @param num
	* @param @return
	* @return boolean 
	* @throws
	 */
	@Update("UPDATE \"tb_param_config\" SET param_value=#{paramValue} where \"param_config_id\"=#{paramConfigId}")
	boolean update(@Param("paramValue") String paramValue, @Param("paramConfigId") String paramConfigId);

	/**
	* @Description: 根据配置项的名称查询配置项
	* @param @return
	* @return SysConfig 
	* @throws
	 */
	@Select("SELECT \"param_config_id\" as paramConfigId, \"param_name\" as paramName, \"param_code\" as paramCode, \"param_value\" as paramValue, \"remark\" as remark from \"tb_param_config\" WHERE \"param_name\"=#{value}")
	SysConfig query(String paramName);

}
