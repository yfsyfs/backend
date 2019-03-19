package com.hikvision.nanchang.doorguardblacklist.po;

import lombok.Data;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 设备 
* @author xiexin6  
* @date 2019年3月6日  
*
 */
@Data
public class Device {
	/**数据库物理主键*/
	private String id;
	/**设备名称*/
	private String cn;

	/**设备类型*/
	private String deviceType;

	/**设备型号*/
	private String deviceModel;

	/**设备IP*/
	private String ip;

	/**设备端口*/
	private int port;

	/**设备编号*/
	private String indexcode;

	/**资源id（条目的rdn就是这个）*/
	private String resourceId;

	/**密码强度*/
	private Integer pwdStrength;

	/**设备是否开启黑名单功能 on 表示开启, off表示关闭*/
	private String status;

	@Override
	public String toString() {
		// 仅仅是LDAP返回的业务字段
		return "Device [cn=" + cn + ", deviceType=" + deviceType + ", deviceModel=" + deviceModel + ", ip=" + ip
				+ ", port=" + port + ", indexcode=" + indexcode + ", resourceId=" + resourceId + ", pwdStrength="
				+ pwdStrength + "]";
	}

}
