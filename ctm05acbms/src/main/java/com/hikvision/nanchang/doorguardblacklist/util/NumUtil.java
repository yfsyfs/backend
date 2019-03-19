package com.hikvision.nanchang.doorguardblacklist.util;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 转换数字工具类
* @author xiexin6  
* @date 2019年3月19日  
*
 */
public class NumUtil {

	private NumUtil() {

	}

	/**
	* @Description: 将对象（一般是一个字符串）转换为整数
	* @param @param object
	* @param @return
	* @return Integer 
	* @throws
	 */
	public static Integer transform2Integer(Object object) {
		return Integer.parseInt(((String) object));
	}

}
