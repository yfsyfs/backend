package com.hikvision.nanchang.doorguardblacklist.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hikvision.nanchang.doorguardblacklist.service.RegionService;
import com.hikvision.nanchang.doorguardblacklist.treevo.RegionTreeVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 区域控制器
* @author xiexin6  
* @date 2019年3月18日  
*
 */
@RestController
@RequestMapping("/region")
@Api(tags = "RegionController", description = "区域接口")
public class RegionController {

	@Autowired
	private RegionService regionService;

	/**
	* @Description: 懒加载区域树或者搜索区域树   
	* @param @param regionId
	* @param @param cn
	* @param @return
	* @return List<RegionTreeVO> 
	* @throws
	 */
	@PostMapping("/query")
	@ApiOperation(value = "懒加载区域树或者搜索区域树", notes = "懒加载区域树或者搜索区域树", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "regionId", value = "区域regionId", paramType = "query"),
			@ApiImplicitParam(name = "cn", value = "区域名称", paramType = "query") })
	public List<RegionTreeVO> query(String regionId, String cn) {
		return regionService.query(regionId, cn);
	}

}
