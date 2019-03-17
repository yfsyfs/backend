package com.hikvision.nanchang.doorguardblacklist.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hikvision.nanchang.doorguardblacklist.dto.DTO;
import com.hikvision.nanchang.doorguardblacklist.po.BlackListPerson;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistPersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 黑名单人员控制器
* @author xiexin6  
* @date 2019年3月11日  
*
 */
@RestController
@RequestMapping("/blacklistperson")
@Api(tags = "BlacklistPersonController", description = "黑名单人员接口")
public class BlacklistPersonController {

	@Autowired
	private BlacklistPersonService blacklistPersonService;

	/**
	* @Description: 黑名单人员条件分页查询   
	* @param @param params
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/queryByConditions")
	@ApiOperation(value = "黑名单人员条件分页查询   ", notes = "黑名单人员条件分页查询   ", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "params", value = "提交参数", paramType = "body")
	public DTO queryByConditions(@RequestBody Map<String, Object> params) {
		return DTO.success(blacklistPersonService.queryByConditions(params));
	}

	/**
	* @Description: 新增永久黑名单人员   
	* @param @param blackListPerson
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/insert")
	@ApiOperation(value = "新增永久黑名单人员", notes = "新增永久黑名单人员", produces = "application/json")
	@ApiImplicitParam(name = "blackListPerson", value = "黑名单人员", paramType = "body", dataType = "BlackListPerson")
	public DTO insert(@RequestBody BlackListPerson blackListPerson) {
		return DTO.success(blacklistPersonService.insert(blackListPerson));
	}

	/**
	* @Description: 修改永久黑名单人员信息   
	* @param @param blackListPerson
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改永久黑名单人员信息", notes = "修改永久黑名单人员信息", produces = "application/json")
	@ApiImplicitParam(name = "blackListPerson", value = "黑名单人员", paramType = "body", dataType = "BlackListPerson")
	public DTO update(@RequestBody BlackListPerson blackListPerson) {
		return DTO.success(blacklistPersonService.update(blackListPerson));
	}

	/**
	* @Description: 批量删除永久黑名单列表 
	* @param @param blackListPersons
	* @param @return
	* @return DTO 
	* @throws
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "批量删除永久黑名单列表 ", notes = "批量删除永久黑名单列表 ", produces = "application/json")
	public DTO remove(String[] ids) {
		return DTO.success(blacklistPersonService.remove(ids));
	}

}
