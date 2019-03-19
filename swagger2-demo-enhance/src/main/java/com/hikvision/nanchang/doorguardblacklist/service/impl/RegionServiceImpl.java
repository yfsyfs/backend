package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.hikvision.nanchang.doorguardblacklist.common.Constants;
import com.hikvision.nanchang.doorguardblacklist.mapper.ldap.RegionLdapMapper;
import com.hikvision.nanchang.doorguardblacklist.service.RegionService;
import com.hikvision.nanchang.doorguardblacklist.treevo.RegionTreeVO;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description:  RegionService的实现类
* @author xiexin6  
* @date 2019年3月18日  
*
 */
@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public List<RegionTreeVO> query(String regionId, String cn) {
		// 如果不是搜寻某区域, 而只是为了懒加载某个节点的儿子节点（不包括孙子节点）
		if (StringUtils.isBlank(cn)) {
			return ldapTemplate.search(Constants.REGION_BASE_DN, "regionId=*", SearchControls.ONELEVEL_SCOPE,
					new RegionLdapMapper());
			// 如果是搜寻某区域
		} else {
			List<RegionTreeVO> regionTreeVOs = ldapTemplate.search(Constants.REGION_BASE_DN,
					"(&(regionId=*)(cn=*" + cn + "*))", SearchControls.SUBTREE_SCOPE, new RegionLdapMapper());
			List<RegionTreeVO> ret = new ArrayList<>(regionTreeVOs);
			for (RegionTreeVO regionTreeVO : regionTreeVOs) {
				String[] regionIds = regionTreeVO.getRegionPath().split(",");
				for (int i = 1; i < regionIds.length - 1; i++) {
					RegionTreeVO treeVO = new RegionTreeVO(regionIds[i], regionIds[i - 1],
							query(regionIds[i], null).get(0).getCn());
					if (!ret.contains(treeVO)) {
						ret.add(treeVO);
					}
				}
			}
			return ret;
		}
	}

}
