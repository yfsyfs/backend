package com.hikvision.nanchang.doorguardblacklist.po;

import java.util.Date;

/**
 * 
 * 海康威视数字技术股份有限公司 南昌分公司
 * 
 * @Description: 黑名单实体类
 * @author xiexin6
 * @date 2019年3月2日
 *
 */
public class BlacklistEvent {

	/**数据库主键ID*/
	private String id;

	/** 身份证id */
	private String IDCARD;

	/** 姓名 */
	private String name;

	/** 黑名单事件产生时间 */
	private Date happenTime;

	/**黑名单设备的编号*/
	private String indexcode;

	/** 黑名单事件发生地点 */
	private String happenAddress;

	/** 事件类型(对于本组件，只会取3个值，详见Constants类) */
	private Integer eventType;

	/**事件类型名称(对于本组件, 只会取三个值, 详见Constants类)*/
	private String eventName;

	/**图片的url*/
	private String eventPictureURL;

	public String getIndexcode() {
		return indexcode;
	}

	public void setIndexcode(String indexcode) {
		this.indexcode = indexcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIDCARD() {
		return IDCARD;
	}

	public void setIDCARD(String iDCARD) {
		IDCARD = iDCARD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	public String getHappenAddress() {
		return happenAddress;
	}

	public void setHappenAddress(String happenAddress) {
		this.happenAddress = happenAddress;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventPictureURL() {
		return eventPictureURL;
	}

	public void setEventPictureURL(String eventPictureURL) {
		this.eventPictureURL = eventPictureURL;
	}

	public BlacklistEvent(String IDCARD, String name, Date happenTime, String indexcode, String happenAddress,
			Integer eventType, String eventName, String eventPictureURL) {
		this.IDCARD = IDCARD;
		this.name = name;
		this.happenTime = happenTime;
		this.indexcode = indexcode;
		this.happenAddress = happenAddress;
		this.eventType = eventType;
		this.eventName = eventName;
		this.eventPictureURL = eventPictureURL;
	}

	public BlacklistEvent() {
	}

	@Override
	public String toString() {
		return "BlacklistEvent [id=" + id + ", IDCARD=" + IDCARD + ", name=" + name + ", happenTime=" + happenTime
				+ ", indexcode=" + indexcode + ", happenAddress=" + happenAddress + ", eventType=" + eventType
				+ ", eventName=" + eventName + ", eventPictureURL=" + eventPictureURL + "]";
	}

}
