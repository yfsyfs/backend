package com.hikvision.nanchang.doorguardblacklist.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("系统配置实体")
public class SysConfig {
	/**数据库主键*/
	@ApiModelProperty(hidden = true)
	private String paramConfigId;

	/**配置项的名称*/
	@ApiModelProperty(hidden = true)
	private String paramName;

	/**配置项的code*/
	@ApiModelProperty(hidden = true)
	private String paramCode;

	/**配置项的值*/
	@ApiModelProperty(value = "黑名单设备阈值", required = true, example = "3")
	private String paramValue;

	/**配置项描述*/
	@ApiModelProperty(hidden = true)
	private String remark;
}
