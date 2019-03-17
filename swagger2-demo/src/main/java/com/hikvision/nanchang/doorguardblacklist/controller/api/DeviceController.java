package com.hikvision.nanchang.doorguardblacklist.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hikvision.nanchang.doorguardblacklist.common.Page;
import com.hikvision.nanchang.doorguardblacklist.dto.DTO;
import com.hikvision.nanchang.doorguardblacklist.po.Device;
import com.hikvision.nanchang.doorguardblacklist.service.DeviceLdapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 设备控制器
* @author xiexin6  
* @date 2019年3月11日  
*
 */
@RestController
@RequestMapping("/device")
@Api(tags = "DeviceController", description = "设备接口")
public class DeviceController {

	@Autowired
	private DeviceLdapService deviceLdapService;

	/**
	* @Description: 分页条件查询开启了黑名单的设备   
	* @param @param params
	* @param @param page
	* @param @return
	* @return DTO 
	* @throws Exception
	 */
	@PostMapping("/queryBlacklistDeviceByConditions")
	@ApiOperation(value = "分页条件查询开启了黑名单的设备  ", notes = "分页条件查询开启了黑名单的设备  ", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "params", value = "提交参数", paramType = "body"),
			@ApiImplicitParam(name = "page", value = "分页参数", paramType = "body") })
	public DTO queryBlacklistDeviceByConditions(@RequestBody Map<String, Object> params, @RequestBody Page<Device> page)
			throws Exception {
		return DTO.success(deviceLdapService.queryBlacklistDeviceByConditions(params, page));
	}

	/**
	* @Description: 分页条件查询所有设备   
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/queryDeviceByConditions")
	@ApiOperation(value = "分页条件查询所有设备 ", notes = "分页条件查询所有设备", produces = "application/json")
	@ApiImplicitParam(name = "cn", value = "设备名称")
	public DTO queryDeviceByConditions(String cn) {
		return DTO.success(deviceLdapService.queryDeviceByConditions(cn));
	}

	/**
	* @Description: 根据设备资源id列表移除设备
	* @param @param resourceIds
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "根据设备资源id列表移除设备 ", notes = "根据设备资源id列表移除设备", produces = "application/json")
	public DTO remove(String[] resourceIds) {
		return DTO.success(deviceLdapService.remove(resourceIds));
	}

	/**
	* @Description: 批量开启设备的黑名单功能   
	* @param @param resourceIds
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/add")
	@ApiOperation(value = "批量开启设备的黑名单功能  ", notes = "批量开启设备的黑名单功能", produces = "application/json")
	@ApiImplicitParam(name = "resourceIds", value = "设备资源id列表")
	public DTO add(String[] resourceIds) {
		return DTO.success(deviceLdapService.add(resourceIds));
	}

}
