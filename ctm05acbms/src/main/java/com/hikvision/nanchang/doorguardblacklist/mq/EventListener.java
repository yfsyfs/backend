package com.hikvision.nanchang.doorguardblacklist.mq;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hikvision.nanchang.doorguardblacklist.common.Constants;
import com.hikvision.nanchang.doorguardblacklist.exception.HikBlacklistException;
import com.hikvision.nanchang.doorguardblacklist.po.BlackListPerson;
import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistEventService;
//import com.hikvision.nanchang.doorguardblacklist.service.BlacklistPersonService;
import com.hikvision.nanchang.doorguardblacklist.service.DeviceLdapService;
import com.hikvision.nanchang.doorguardblacklist.service.SysconfigService;
import com.hikvision.nanchang.doorguardblacklist.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 接受MQ消息过滤出黑名单事件、人证比对成功、人证比对失败事件 
* @author xiexin6  
* @date 2019年3月6日  
*
 */
@Component
@Slf4j
public class EventListener implements MessageListener {

	@Autowired
	private DeviceLdapService deviceLdapService;

	@Autowired
	private BlacklistEventService blacklistEventService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

//	@Autowired
//	private BlacklistPersonService blacklistPersonService;

	@Autowired
	private SysconfigService sysconfigService;

	@Override
	public void onMessage(Message message) {
		// TODO 加日志
		log.debug("收到门禁组件MQ报文并开始处理.");
		try {
			// 得到mq报文
			String json = ((TextMessage) message).getText();
			// 封装成黑名单事件列表
			List<BlacklistEvent> blacklistEvents = fromJSON2Blacklist(json);
			for (BlacklistEvent blacklistEvent : blacklistEvents) {
				// 身份证号码
				String id = blacklistEvent.getIDCARD();
				// 产生黑名单事件的门禁设备的编号
				String indexcode = blacklistEvent.getIndexcode();
				if (StringUtils.isBlank(id) || StringUtils.isBlank(indexcode)) {
					// 身份证号码是我们需要的重要信息，所以不能没有，没有的话，我们就视之为无效数据
					// 设备编号也是我们需要的重要信息（相当于知道黑名单事件发生在哪台设备上, 进而知道黑名单事件的发生地点）,如果没有的话, 我们不处理此事件
					// TODO 加日志
					continue;
				}
				blacklistEventService.insert(blacklistEvent);
				log.debug("已经成功插入黑名单————" + JSONUtils.toJSONString(blacklistEvent));
				// 处理临时黑名单逻辑
				handle(id, blacklistEvent.getName(),
						Integer.parseInt(sysconfigService.query("threshold").getParamValue()));
				log.debug("已经成功处理临时黑名单逻辑————" + JSONUtils.toJSONString(Arrays.asList(id, blacklistEvent.getName(),
						sysconfigService.query("threshold").getParamValue())));
			}
		} catch (Exception e) {
			log.error("出现异常: " + e);
			throw new HikBlacklistException(Constants.MESSAGE_PROCESS_ERROR_CODE, Constants.MESSAGE_PROCESS_ERROR_MSG);
		}
	}

	/**
	* @Description: 处理临时黑名单逻辑   
	* @param @param id
	* @param @param name
	* @param @param indexcode
	* @return void 
	* @throws
	 */
	protected void handle(String id, String name, Integer threshold) {
		String count = redisTemplate.opsForValue().get(id);
		// 如果设备无限制
		if (threshold == 0) {
			return;
		}
		int num = count == null ? 0 : Integer.parseInt(count);
		if (num < threshold - 1) {
			redisTemplate.opsForValue().set(id, "" + (++num), DateUtil.tomorrow(), TimeUnit.MILLISECONDS);
		} else {
			// 插入临时黑名单
//			blacklistPersonService.insert(new BlackListPerson(id, name, 0, null));
		}
	}

	/**
	* @Description: 解析mq中的json报文然后封装成黑名单实体列表   
	* @param @param json
	* @param @return
	* @return List<Blacklist> 
	* @throws
	 */
	protected List<BlacklistEvent> fromJSON2Blacklist(String json) throws ParseException {
		List<BlacklistEvent> blacklists = new ArrayList<>();
		JSONObject jsonObject = JSONObject.parseObject(json);
		// 一条MQ报文中可能报告多个事件
		JSONArray events = ((JSONArray) ((JSONObject) (jsonObject.get("params"))).get("events"));
		for (int i = 0; i < events.size(); i++) {
			JSONObject event = (JSONObject) events.get(i);
			// 事件类型
			Integer eventType = (Integer) event.get("eventType");
			String eventName = null;
			if (Constants.BLACKLIST_EVENT_TYPE.equals(eventType)) {
				eventName = Constants.BLACKLIST_EVENT_TYPE_NAME;
			} else if (Constants.FACE_CERTIFICATE_COMPARE_SUCCESS_TYPE.equals(eventType)) {
				eventName = Constants.FACE_CERTIFICATE_COMPARE_SUCCESS_NAME;
			} else if (Constants.FACE_CERTIFICATE_COMPARE_FAILED_TYPE.equals(eventType)) {
				eventName = Constants.FACE_CERTIFICATE_COMPARE_FAILED_NAME;
			} else {
				// 本组件 只处理 黑名单事件和人证比对成功、人证比对失败事件
				continue;
			}
			// 发生时间
			Date happenTime = DateUtil.parse((String) (event.get("happenTime")));
			// 设备编号
			String srcIndex = event.getString("srcIndex");
			// 设备名称
			String happenAddress = deviceLdapService.queryByIndexcode(srcIndex).getCn();
			// 图片的url
			String extEventPictureURL = ((JSONObject) event.get("data")).getString("ExtEventPictureURL");
			// 得到身份证信息
			JSONObject extEventIdentityCardInfo = (JSONObject) ((JSONObject) event.get("data"))
					.get("ExtEventIdentityCardInfo");
			// 身份证号
			String ID = extEventIdentityCardInfo.getString("IdNum");
			// 姓名
			String name = extEventIdentityCardInfo.getString("Name");
			blacklists.add(new BlacklistEvent(ID, name, happenTime, srcIndex, happenAddress, eventType, eventName,
					extEventPictureURL));
		}
		return blacklists;
	}

}
