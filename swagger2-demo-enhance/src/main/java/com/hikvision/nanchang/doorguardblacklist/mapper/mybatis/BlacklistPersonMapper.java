package com.hikvision.nanchang.doorguardblacklist.mapper.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.provider.BlacklistPersonDynamicSqlProvider;
import com.hikvision.nanchang.doorguardblacklist.po.BlackListPerson;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 黑名单人员mapper
* @author xiexin6  
* @date 2019年3月11日  
*
 */
public interface BlacklistPersonMapper {

	/**
	* @Description: 条件查询 黑名单人员列表
	* @param blackListPerson
	* @return List<BlackListPerson> 
	* @throws
	 */
	@SelectProvider(type = BlacklistPersonDynamicSqlProvider.class, method = "queryByConditions")
	List<BlackListPerson> queryByConditions(Map<String, Object> params);

	/**
	* @Description: 新增黑名单人员   
	* @param @param person
	* @param @return
	* @return int 
	* @throws
	 */
	@InsertProvider(type = BlacklistPersonDynamicSqlProvider.class, method = "insert")
	int insert(BlackListPerson blackListPerson);

	/**
	* @Description: 修改永久黑名单人员   
	* @param @param blackListPerson
	* @param @return
	* @return boolean 
	* @throws
	 */
	@UpdateProvider(type = BlacklistPersonDynamicSqlProvider.class, method = "update")
	boolean update(BlackListPerson blackListPerson);

	/**
	* @Description: 删除永久黑名单人员   
	* @param @param blackListPerson
	* @param @return
	* @return boolean 
	* @throws
	 */
	@Delete("DELETE FROM \"TB_BLACKLIST_PERSON_PERMANENT\" WHERE \"id\" = #{id}")
	boolean remove(@Param("id") String id);

	@Delete("DELETE FROM \"TB_BLACKLIST_PERSON_PERMANENT\"")
	boolean clean();

}
