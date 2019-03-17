package com.hikvision.nanchang.doorguardblacklist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 系统配置
* @author xiexin6  
* @date 2019年3月16日  
*
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig {
	/**数据库主键*/
	private String paramConfigId;

	/**配置项的名称*/
	private String paramName;

	/**配置项的code*/
	private String paramCode;

	/**配置项的值*/
	private String paramValue;

	/**配置项描述*/
	private String remark;
}
