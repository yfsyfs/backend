package com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.hikvision.nanchang.doorguardblacklist.po.BlackListPerson;

/**
* 海康威视数字技术股份有限公司 南昌分公司 
* @Description: 提供黑名单人员有关动态SQL
* @author xiexin6  
* @date 2019年3月7日  
*
 */
public class BlacklistPersonDynamicSqlProvider {

	/**
	* @Description:生成动态select语句   
	* @param @param params
	* @param @return
	* @return String 
	* @throws
	 */
	public String queryByConditions(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				// 根据传入的类型, 到底是从TB_BLACKLIST_PERSON_TMP中查还是从 TB_BLACKLIST_PERSON_PERMANENT中查
				FROM((((Integer) params.get("type")) == 0) ? "\"TB_BLACKLIST_PERSON_TMP\""
						: "\"TB_BLACKLIST_PERSON_PERMANENT\"");
				if (params.get("name") != null) {
					WHERE(" \"name\" LIKE CONCAT('%',#{name}, '%') ");
				}
				if (params.get("id") != null) {
					WHERE(" \"id\" = #{id} ");
				}
				if (params.get("description") != null) {
					WHERE(" \"description\" LIKE CONCAT('%',#{description}, '%') ");
				}
			}
		}.toString();
		if (params.get("page") != null) {
			sql += " limit #{page.size} offset #{page.offset}  ";
		}
		return sql;
	}

	/**
	* @Description: 生成动态insert语句   
	* @param @param blackListPerson
	* @param @return
	* @return String 
	* @throws
	 */
	public String insert(BlackListPerson blackListPerson) {
		return new SQL() {
			{
				// 根据插入人员的类型判断到底是插入临时黑名单人员表还是 永久黑名单人员表
				INSERT_INTO("\"" + ((blackListPerson.getType() == 0) ? "TB_BLACKLIST_PERSON_TMP"
						: "TB_BLACKLIST_PERSON_PERMANENT") + "\"");
				if (blackListPerson.getId() != null) {
					VALUES("\"id\"", "'" + blackListPerson.getId() + "'");
				}
				if (blackListPerson.getName() != null) {
					VALUES("\"name\"", "'" + blackListPerson.getName() + "'");
				}
				if (blackListPerson.getDescription() != null) {
					VALUES("\"description\"", "'" + blackListPerson.getDescription() + "'");
				}
			}
		}.toString();
	}

	/**
	* @Description: 生成动态update语句   
	* @param @param blackListPerson
	* @param @return
	* @return String 
	* @throws
	 */
	public String update(BlackListPerson blackListPerson) {
		return new SQL() {
			{
				UPDATE("\"TB_BLACKLIST_PERSON_PERMANENT\"");
				if (blackListPerson.getName() != null) {
					SET("\"name\"=#{name}");
				}
				if (blackListPerson.getDescription() != null) {
					SET("\"description\"=#{description}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}

}
