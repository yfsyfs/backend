package com.hikvision.nanchang.doorguardblacklist.mapper.mybatis.provider;

import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.jdbc.SQL;

import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;
import com.hikvision.nanchang.doorguardblacklist.util.DateUtil;
import com.hikvision.nanchang.doorguardblacklist.util.NumUtil;

/**
* 海康威视数字技术股份有限公司 南昌分公司 
* @Description: 提供黑名单事件有关动态SQL
* @author xiexin6  
* @date 2019年3月7日  
*
 */
public class BlacklistEventDynamicSqlProvider {

	/**
	* @Description: 条件分页查询  
	* @param @param params
	* @param @return
	* @return String 
	* @throws
	 */
	public String selectWithParams(final Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("\"idnum\" as IDCARD,\"name\", \"happen_time\" as happenTime, \"happen_address\" as happenAddress, \"event_type\" as eventType,\"event_name\" as eventName, \"event_picture_url\" as eventPictureURL");
				FROM("\"tb_event\"");
				if (params.get("name") != null) {
					WHERE(" \"name\" LIKE CONCAT('%',#{name}, '%') ");
				}
				if (params.get("IDCARD") != null) {
					WHERE(" \"idnum\" = #{IDCARD} ");
				}
				if (params.get("happenAddress") != null) {
					WHERE(" \"happen_address\" LIKE CONCAT('%',#{happenAddress}, '%') ");
				}
				if (params.get("eventType") != null) {
					WHERE(" event_type = #{eventType} ");
				}
				if (params.get("hasPicture") != null) {
					// 有抓拍图片
					if ((int) params.get("hasPicture") == 1) {
						WHERE(" \"event_picture_url\" is not null ");
						// 无抓拍图片
					} else if ((int) params.get("hasPicture") == 2) {
						WHERE(" \"event_picture_url\" is null ");
					}
				}
				if (params.get("beginTime") != null) {
					WHERE(" \"happen_time\" >= #{beginTime} ");
				}
				if (params.get("endTime") != null) {
					WHERE(" \"happen_time\" <= #{endTime} ");
				}
				if (params.get("orderStr") != null) {
					String order = (String) params.get("order");
					order = " " + (order == null ? "ASC" : order);
					ORDER_BY((String) params.get("sort") + order);
				}

			}
		}.toString();
		if (params.get("pageSize") != null && params.get("pageNo") != null) {
			Integer pageSize = NumUtil.transform2Integer(params.get("pageSize"));
			Integer pageNo = NumUtil.transform2Integer(params.get("pageNo"));
			params.put("offset", (pageNo - 1) * pageSize + 1);
			sql += " limit #{pageSize} offset #{offset}";
		}
		return sql;
	}

	/**
	* @Description: 条件插入
	* @param @param params
	* @param @return
	* @return String 
	* @throws
	 */
	public String insertWithParams(final BlacklistEvent blacklistEvent) {
		return new SQL() {
			{
				INSERT_INTO("\"tb_event\"");
				VALUES("\"id\"", "'" + UUID.randomUUID().toString().replaceAll("-", "") + "'");
				if (blacklistEvent.getIDCARD() != null) {
					VALUES("\"idnum\"", "'" + blacklistEvent.getIDCARD() + "'");
				}
				if (blacklistEvent.getName() != null) {
					VALUES("\"name\"", "'" + blacklistEvent.getName() + "'");
				}
				if (blacklistEvent.getHappenTime() != null) {
					VALUES("\"happen_time\"", "'" + DateUtil.format(blacklistEvent.getHappenTime()) + "'");
				}
				if (blacklistEvent.getHappenAddress() != null) {
					VALUES("\"happen_address\"", "'" + blacklistEvent.getHappenAddress() + "'");
				}
				if (blacklistEvent.getEventType() != null) {
					VALUES("\"event_type\"", blacklistEvent.getEventType().toString());
				}
				if (blacklistEvent.getEventName() != null) {
					VALUES("\"event_name\"", "'" + blacklistEvent.getEventName() + "'");
				}
				if (blacklistEvent.getEventPictureURL() != null) {
					VALUES("\"event_picture_url\"", "'" + blacklistEvent.getEventPictureURL() + "'");
				}
			}
		}.toString();
	}

}
