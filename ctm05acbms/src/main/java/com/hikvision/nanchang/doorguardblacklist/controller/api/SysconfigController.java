package com.hikvision.nanchang.doorguardblacklist.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hikvision.nanchang.doorguardblacklist.dto.DTO;
import com.hikvision.nanchang.doorguardblacklist.service.SysconfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 系统配置控制器
* @author xiexin6  
* @date 2019年3月13日  
*
 */
@RestController
@RequestMapping("/sysconfig")
@Api(tags = "SysconfigController", description = "系统配置接口")
public class SysconfigController {

	@Autowired
	private SysconfigService sysconfigService;

	/**
	* @Description: 更新黑名单设备报警阈值   
	* @param @param num
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/updateThreshold")
	@ApiOperation(value = "更新黑名单设备报警阈值", notes = "更新黑名单设备报警阈值", produces = "application/json")
	@ApiImplicitParam(name = "num", value = "设备阈值", paramType = "query")
	public DTO updateThreshold(Integer num) {
		return DTO.success(sysconfigService.update(num.toString(), "threshold"));
	}

	/**
	* @Description: 查询黑名单设备报警阈值   
	* @param @return
	* @return DTO 
	* @throws
	 */
	@GetMapping("/queryThreshold")
	@ApiOperation(value = "查询黑名单设备报警阈值", notes = "查询黑名单设备报警阈值", produces = "application/json")
	public DTO queryThreshold() {
		return DTO.success(sysconfigService.query("threshold").getParamValue());
	}
}
