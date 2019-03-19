package com.hikvision.nanchang.doorguardblacklist.mapper.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.hikvision.nanchang.doorguardblacklist.po.Device;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 映射ldap服务器返回的数据到实体类 
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public class DeviceLdapMapper implements AttributesMapper<Device> {

	@Override
	public Device mapFromAttributes(Attributes attributes) throws NamingException {
		Device device = null;
		if (attributes != null) {
			device = new Device();
			Attribute cnAttr = attributes.get("cn");
			// 设备名
			if (cnAttr != null) {
				device.setCn((String) (cnAttr.get()));
			}
			// 设备类型
			Attribute deviceTypeAttr = attributes.get("deviceType");
			if (deviceTypeAttr != null) {
				device.setDeviceType((String) (deviceTypeAttr.get()));
			}
			// 设备型号
			Attribute deviceModelAttr = attributes.get("deviceModel");
			if (deviceModelAttr != null) {
				device.setDeviceModel((String) (deviceModelAttr.get()));
			}
			// 设备IP
			Attribute iPAttr = attributes.get("ip");
			if (iPAttr != null) {
				device.setIp((String) (iPAttr.get()));
			}
			// 设备端口
			Attribute portAttr = attributes.get("port");
			if (portAttr != null) {
				device.setPort(Integer.parseInt((String) portAttr.get()));
			}
			// 设备编号
			Attribute indexcodeAttr = attributes.get("indexcode");
			if (indexcodeAttr != null) {
				device.setIndexcode((String) (indexcodeAttr.get()));
			}
			// 设备编号
			Attribute resourceIdAttr = attributes.get("resourceId");
			if (resourceIdAttr != null) {
				device.setResourceId((String) (resourceIdAttr.get()));
			}
			// 密码强度
			Attribute pwdStrengthAttr = attributes.get("pwdStrength");
			if (pwdStrengthAttr != null) {
				device.setPwdStrength((Integer.parseInt((String) (pwdStrengthAttr.get()))));
			}
		}
		return device;
	}

}
