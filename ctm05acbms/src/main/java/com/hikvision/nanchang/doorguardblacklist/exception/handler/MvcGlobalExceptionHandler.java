package com.hikvision.nanchang.doorguardblacklist.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hikvision.nanchang.doorguardblacklist.dto.DTO;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 全局异常处理器
* @author xiexin6  
* @date 2019年3月13日  
*
 */
@RestControllerAdvice
public class MvcGlobalExceptionHandler {
	@ExceptionHandler
	public DTO handleException() {
		return DTO.failed();
	}
}
