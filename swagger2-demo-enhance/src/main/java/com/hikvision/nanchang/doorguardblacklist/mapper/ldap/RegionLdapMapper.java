package com.hikvision.nanchang.doorguardblacklist.mapper.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.hikvision.nanchang.doorguardblacklist.treevo.RegionTreeVO;

public class RegionLdapMapper implements AttributesMapper<RegionTreeVO> {

	@Override
	public RegionTreeVO mapFromAttributes(Attributes attributes) throws NamingException {
		RegionTreeVO regionTreeVO = null;
		if (attributes != null) {
			regionTreeVO = new RegionTreeVO();
			if (attributes.get("cn") != null) {
				regionTreeVO.setCn((String) attributes.get("cn").get());
			}
			if (attributes.get("regionId") != null) {
				regionTreeVO.setRegionId((String) attributes.get("regionId").get());
			}
			if (attributes.get("regionPath") != null) {
				String regionPath = (String) attributes.get("regionPath").get();
				regionTreeVO.setRegionPath(regionPath);
				String[] regionIds = regionPath.split(",");
				regionTreeVO.setParentRegionId(regionIds[regionIds.length - 2]);
			}
		}
		return regionTreeVO;
	}

}
