package com.hikvision.nanchang.doorguardblacklist.treevo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 区域树VO
* @author xiexin6  
* @date 2019年3月18日  
*
 */
@Data
public class RegionTreeVO {

	/**区域树regionId（ldap服务器上的regionId）*/
	private String regionId;

	/**父节点的regionId*/
	private String parentRegionId;

	/**节点的regionPath字段*/
	@JsonIgnore
	private String regionPath;

	/**节点名称*/
	private String cn;

	@Override
	public String toString() {
		return "RegionTreeVO [regionId=" + regionId + ", parentRegionId=" + parentRegionId + ", cn=" + cn + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegionTreeVO other = (RegionTreeVO) obj;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		return true;
	}

	public RegionTreeVO(String regionId, String parentRegionId, String cn) {
		this.regionId = regionId;
		this.parentRegionId = parentRegionId;
		this.cn = cn;
	}

	public RegionTreeVO() {
	}
}
