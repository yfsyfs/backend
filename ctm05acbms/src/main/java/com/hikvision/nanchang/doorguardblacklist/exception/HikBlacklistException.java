package com.hikvision.nanchang.doorguardblacklist.exception;

/**
 * 
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 黑名单 异常类
* @author xiexin6  
* @date 2019年3月6日  
*
 */
public class HikBlacklistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**响应码*/
	private Integer code;

	/**响应信息*/
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HikBlacklistException(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
