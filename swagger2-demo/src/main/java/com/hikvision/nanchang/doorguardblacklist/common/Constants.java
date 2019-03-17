package com.hikvision.nanchang.doorguardblacklist.common;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 常量类 
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public class Constants {

	/*********************************事件类型码开始**********************************/
	/**黑名单事件事件码*/
	public static final Integer BLACKLIST_EVENT_TYPE = 66323;
	/**黑名单事件类型名*/
	public static final String BLACKLIST_EVENT_TYPE_NAME = "黑名单事件";
	/**人证比对成功事件事件码*/
	public static final Integer FACE_CERTIFICATE_COMPARE_SUCCESS_TYPE = 1;
	/**人证比对成功事件类型名*/
	public static final String FACE_CERTIFICATE_COMPARE_SUCCESS_NAME = "人证比对成功事件";
	/**人证比对失败事件事件码*/
	public static final Integer FACE_CERTIFICATE_COMPARE_FAILED_TYPE = 2;
	/**人证比对失败事件类型名*/
	public static final String FACE_CERTIFICATE_COMPARE_FAILED_NAME = "人证比对失败事件";
	/*********************************事件类型码结束**********************************/

	/*******************************异常相关常量类开始****************************************/

	/*********************************异常消息开始*************************************/
	public static final Integer NOT_FOUND_DEVICE_CODE = 1001;
	public static final Integer DUPLICATE_DEVICE_FOUND_BY_INDEXCODE_CODE = 1002;
	public static final Integer MESSAGE_PROCESS_ERROR_CODE = 1003;
	public static final Integer CSV_EXPORT_ERROR_CODE = 1004;
	/*********************************异常消息结束*************************************/

	/*********************************异常响应码开始*************************************/
	public static final String NOT_FOUND_DEVICE_MSG = "没有找到设备.";
	public static final String DUPLICATE_DEVICE_FOUND_BY_INDEXCODE_MSG = "根据设备编号找到了重复设备";
	public static final String MESSAGE_PROCESS_ERROR_MSG = "消息处理失败";
	public static final String CSV_EXPORT_ERROR_MSG = "导出CSV格式文件失败";
	/*********************************异常响应码结束*************************************/

	/*******************************异常相关常量类结束****************************************/

	/****************************************设备相关开始********************************************/
	public static final String DEVICE_BASE_DN = "regionId=e682119d-4cf3-48bc-99d9-1931727fc305,regionId=root000000,ou=region,dc=businessview,dc=platform";
	/****************************************设备相关结束**************************************************/

	/****************************************swagger2相关********************************************/
	public static final String SWAGGER2_BASEPACKAGE = "com.hikvision.nanchang.doorguardblacklist.controller.api";
	public static final String SWAGGER2_TITLE = "宜春车管所门禁项目API";
	public static final String SWAGGER2_DESCRIPTION = "宜春车管所门禁项目API文档, 提供前端开发人员测试";
	public static final String SWAGGER2_VERSION = "V1.0.0";
	/****************************************swagger2结束********************************************/

}
