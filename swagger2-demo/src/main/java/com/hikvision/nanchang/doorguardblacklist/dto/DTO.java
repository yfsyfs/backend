package com.hikvision.nanchang.doorguardblacklist.dto;

import lombok.Data;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 前后端交互传输对象 
* @author xiexin6  
* @date 2019年3月6日  
*
 */
@Data
public class DTO {

	/**响应码*/
	private Integer code;

	/**响应消息*/
	private String msg;

	/**数据*/
	private Object data;

	private DTO() {
	}

	/**
	* @Description: 成功   
	* @param @param data
	* @param @return
	* @return DTO 
	* @throws
	 */
	public static DTO success(Object data) {
		DTO dto = new DTO();
		dto.code = 0;
		dto.msg = "success";
		dto.data = data;
		return dto;
	}

	/**
	* @Description: 失败   
	* @param @return
	* @return DTO 
	* @throws
	 */
	public static DTO failed() {
		DTO dto = new DTO();
		dto.code = 1;
		dto.msg = "failed";
		dto.data = null;
		return dto;
	}

	/**
	* @Description: 自定义响应   
	* @param @param code
	* @param @param msg
	* @param @param data
	* @param @return
	* @return DTO 
	* @throws
	 */
	public static DTO response(int code, String msg, Object data) {
		DTO dto = new DTO();
		dto.code = code;
		dto.msg = msg;
		dto.data = data;
		return dto;
	}

}
