package com.hikvision.nanchang.doorguardblacklist.mq;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistEventService;
import com.hikvision.nanchang.doorguardblacklist.test.BaseTest;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 模拟测试 EventListener 处理数据的流程
* @author xiexin6  
* @date 2019年3月9日  
*
 */
public class EventListenerTest extends BaseTest {
	
	@Autowired
	private EventListener eventListener;
	
	@Autowired
	private BlacklistEventService blacklistEventService;

	/**
	* @Description: 模拟测试 fromJSON2Blacklist 方法   
	* @param 
	* @return void 
	* @throws ParseException
	 */
	@Test
	public void testFromJSON2Blacklist() throws ParseException {
		// mq 报文
		String json = "{\r\n" + 
				"    \"method\": \"OnEventNotify\",\r\n" + 
				"    \"params\": {\r\n" + 
				"        \"ability\": \"event_acs\",\r\n" + 
				"        \"events\": [\r\n" + 
				"            {\r\n" + 
				"                \"data\": {\r\n" + 
				"                    \"ExtEventIdentityCardInfo\": {\r\n" + 
				"                        \"Address\": \"\",             \r\n" + 
				"                        \"Birth\": \"\",                \r\n" + 
				"                        \"EndDate\": \"\",              \r\n" + 
				"                        \"IdNum\": \"360102199410233831\",                \r\n" + 
				"                        \"IssuingAuthority\": \"\",     \r\n" + 
				"                        \"Name\": \"影法師\",                 \r\n" + 
				"                        \"Nation\": 0,                \r\n" + 
				"                        \"Sex\": 0,                   \r\n" + 
				"                        \"StartDate\": \"\",            \r\n" + 
				"                        \"TermOfValidity\": 0       \r\n" + 
				"                    },\r\n" + 
				"                    \"ExtAccessChannel\": 0,          \r\n" + 
				"                    \"ExtEventAlarmInID\": 0,         \r\n" + 
				"                    \"ExtEventAlarmOutID\": 0,        \r\n" + 
				"                    \"ExtEventCardNo\": \"\",           \r\n" + 
				"                    \"ExtEventCaseID\": 0,           \r\n" + 
				"                    \"ExtEventCode\": 50362624,       \r\n" + 
				"                    \"ExtEventCustomerNumInfo\": {    \r\n" + 
				"                        \"AccessChannel\": 0,         \r\n" + 
				"                        \"EntryTimes\": 0,            \r\n" + 
				"                        \"ExitTimes\": 0,             \r\n" + 
				"                        \"TotalTimes\": 0             \r\n" + 
				"                    },\r\n" + 
				"                    \"ExtEventDoorID\": 0,            \r\n" + 
				"                    \"ExtEventInOut\": 0,             \r\n" + 
				"                    \"ExtEventLocalControllerID\": 0, \r\n" + 
				"                    \"ExtEventMainDevID\": 1,         \r\n" + 
				"                    \"ExtEventPictureURL\": \"https://yfsyfs.github.io\",       \r\n" + 
				"					\"svrIndexCode\":\"\",\r\n" + 
				"                    \"ExtEventReaderID\": 0,          \r\n" + 
				"                    \"ExtEventReaderKind\": 0,        \r\n" + 
				"                    \"ExtEventReportChannel\": 0,     \r\n" + 
				"                    \"ExtEventRoleID\": 0,            \r\n" + 
				"                    \"ExtEventSubDevID\": 0,          \r\n" + 
				"                    \"ExtEventSwipNum\": 0,           \r\n" + 
				"                    \"ExtEventType\": 66323,           \r\n" + 
				"                    \"ExtEventVerifyID\": 0,          \r\n" + 
				"                    \"ExtEventWhiteListNo\": 0         \r\n" + 
				"                },\r\n" + 
				"                \"eventId\": \"E4BC443EE5C8477B972E2475C32DAA8B\",  \r\n" + 
				"                \"eventType\": 66323,                           \r\n" + 
				"                \"happenTime\": \"2017-12-2823: 00: 03.000\",    \r\n" + 
				"                \"srcIndex\": \"ad324e04-6727-4e62-bffb-93cdfd24f457\",                        \r\n" + 
				"                \"srcName\": \"2605\",                     \r\n" + 
				"                \"srcParentIndex\": \"147000000505\",      \r\n" + 
				"                \"srcType\": \"acsDevice\",               \r\n" + 
				"                \"status\": 0,                         \r\n" + 
				"                \"timeout\": 0                        \r\n" + 
				"            }\r\n" + 
				"        ],\r\n" + 
				"        \"sendTime\": \"2017-12-2822: 57: 31\"\r\n" + 
				"    }\r\n" + 
				"}";
		List<BlacklistEvent> blacklistEvents = eventListener.fromJSON2Blacklist(json);
		for (BlacklistEvent blacklistEvent : blacklistEvents) {
			blacklistEventService.insert(blacklistEvent);
		}
	}
	
	/**
	* @Description: 测试handle方法   
	* @param 
	* @return void 
	* @throws
	 */
	@Test
	public void testHandle() {
		eventListener.handle("360102199410233831", "影法師", 5);
	}

}
