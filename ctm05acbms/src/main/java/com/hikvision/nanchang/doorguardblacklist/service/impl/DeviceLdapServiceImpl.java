package com.hikvision.nanchang.doorguardblacklist.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hikvision.nanchang.doorguardblacklist.common.Constants;
import com.hikvision.nanchang.doorguardblacklist.common.Page;
import com.hikvision.nanchang.doorguardblacklist.exception.HikBlacklistException;
import com.hikvision.nanchang.doorguardblacklist.mapper.ldap.DeviceLdapMapper;
import com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.DeviceMapper;
import com.hikvision.nanchang.doorguardblacklist.po.Device;
import com.hikvision.nanchang.doorguardblacklist.service.DeviceLdapService;
import com.hikvision.nanchang.doorguardblacklist.util.NumUtil;

@Service
public class DeviceLdapServiceImpl implements DeviceLdapService {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public Device queryByIndexcode(String indexcode) {
		List<Device> devices = ldapTemplate.search(Constants.DEVICE_BASE_DN, "indexcode=" + indexcode,
				SearchControls.SUBTREE_SCOPE, new DeviceLdapMapper());
		if (devices == null || devices.isEmpty()) {
			throw new HikBlacklistException(Constants.NOT_FOUND_DEVICE_CODE, Constants.NOT_FOUND_DEVICE_MSG);
		}
		if (devices.size() > 1) {
			throw new HikBlacklistException(Constants.DUPLICATE_DEVICE_FOUND_BY_INDEXCODE_CODE,
					Constants.DUPLICATE_DEVICE_FOUND_BY_INDEXCODE_MSG);
		}
		return devices.get(0);
	}

	@Override
	public Device queryByResourceId(String resourceId) {
		List<Device> devices = ldapTemplate.search(Constants.DEVICE_BASE_DN, "resourceId=" + resourceId,
				SearchControls.SUBTREE_SCOPE, new DeviceLdapMapper());
		if (devices == null || devices.isEmpty()) {
			throw new HikBlacklistException(Constants.NOT_FOUND_DEVICE_CODE, Constants.NOT_FOUND_DEVICE_MSG);
		}
		if (devices.size() > 1) {
			throw new HikBlacklistException(Constants.DUPLICATE_DEVICE_FOUND_BY_INDEXCODE_CODE,
					Constants.DUPLICATE_DEVICE_FOUND_BY_INDEXCODE_MSG);
		}
		return devices.get(0);
	}

	@Override
	public Page<Device> queryBlacklistDeviceByConditions(Map<String, Object> params) throws IOException {
		Page<Device> page = new Page<>();
		int size = NumUtil.transform2Integer(params.get("pageSize"));
		int cur = 1;
		if (params.get("pageNo") != null) {
			cur = NumUtil.transform2Integer(params.get("pageNo"));
		}
		int start = (cur - 1) * size + 1;
		int end = cur * size;
		page.setCur(cur);
		page.setSize(size);
		// 设备名称 这里貌似数据库中要多一个cn字段
		String cn = (String) params.get("cn");
		String order = (String) params.get("order");
		if (order == null) {
			order = "ASC";
		}
		List<Device> devices = null;
		if ("ASC".equalsIgnoreCase(order)) {
			devices = deviceMapper.findAllOnAsc(cn);
		} else {
			devices = deviceMapper.findAllOnDesc(cn);
		}
		List<Device> ret = new ArrayList<>(devices.size());
		int sum = 0;
		if (devices != null) {
			DeviceLdapMapper deviceMapper = new DeviceLdapMapper();
			for (Device device : devices) {
				if (ldapTemplate.search(Constants.DEVICE_BASE_DN, "resourceId=" + device.getResourceId(),
						SearchControls.SUBTREE_SCOPE, deviceMapper) != null) {
					sum++;
				}
				if (sum < start || sum > end) {
					continue;
				} else {
					ret.add(device);
				}
			}
		}
		page.setSum(sum);
		page.setData(ret);
		return page;
	}

	@Override
	public List<Device> queryDeviceByConditions(String cn) {
		List<Device> devices = ldapTemplate.search(Constants.DEVICE_BASE_DN,
				cn == null ? "resourceType=acsDevice" : ("(&(cn=*" + cn + "*)(resourceType=acsDevice))"),
				SearchControls.SUBTREE_SCOPE, new DeviceLdapMapper());
		return devices;
	}

	@CacheEvict(value = "device", allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean add(String[] resourceIds) {
		for (String resourceId : resourceIds) {
			Device device = queryByResourceId(resourceId);
			if (deviceMapper.findOneByResourceId(resourceId) == null) {
				// 鉴于postgresql生成uuid需要导入独立脚本，我们就不为难自己了
				device.setId(UUID.randomUUID().toString().replace("-", ""));
				deviceMapper.add(device);
			} else {
				deviceMapper.on(resourceId);
			}
		}
		return true;
	}

	@CacheEvict(value = "device", allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean remove(String[] resourceIds) {
		for (String resourceId : resourceIds) {
			deviceMapper.off(resourceId);
		}
		return true;
	}

}
