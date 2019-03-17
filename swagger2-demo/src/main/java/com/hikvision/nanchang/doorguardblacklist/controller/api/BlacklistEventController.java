package com.hikvision.nanchang.doorguardblacklist.controller.api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csvreader.CsvWriter;
import com.hikvision.nanchang.doorguardblacklist.common.Constants;
import com.hikvision.nanchang.doorguardblacklist.dto.DTO;
import com.hikvision.nanchang.doorguardblacklist.exception.HikBlacklistException;
import com.hikvision.nanchang.doorguardblacklist.po.BlacklistEvent;
import com.hikvision.nanchang.doorguardblacklist.service.BlacklistEventService;
import com.hikvision.nanchang.doorguardblacklist.util.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 黑名单事件控制器
* @author xiexin6  
* @date 2019年3月11日  
*
 */
@Slf4j
@Controller
@RequestMapping("/blacklistevent")
@Api(tags = "BlacklistEventController", description = "黑名单事件接口")
public class BlacklistEventController {

	@Autowired
	private BlacklistEventService blacklistEventService;

	/**
	* @Description: 黑名单事件条件分页查询   
	* @param @param params
	* @param @return
	* @return DTO 
	* @throws
	 */
	@ResponseBody
	@PostMapping("/queryByConditions")
	@ApiOperation(value = "黑名单事件条件分页查询", notes = "POST方式黑名单事件条件分页查询", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "params", value = "提交参数", paramType = "body")
	public DTO queryByConditions(@RequestBody Map<String, Object> params) {
		return DTO.success(blacklistEventService.queryByConditions(params));
	}

	/**
	* @Description: 查询结果导出csv格式文件 提供下载
	* @param @param params
	* @return void 
	 * @throws IOException 
	 */
	@GetMapping("/toCsv")
	@ApiOperation(value = "导出CSV格式文件", notes = "导出csv格式文件")
	@ApiImplicitParam(name = "params", value = "提交参数", paramType = "query")
	public void toCsv(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<BlacklistEvent> blacklistEvents = blacklistEventService.queryByConditions(params);
		String filename = "查询结果_" + DateUtil.format(new Date()) + ".csv";
		String useragent = request.getHeader("USER-AGENT").toLowerCase();
		// chrome、IE11会走这里
		if (useragent.contains("msie") || useragent.contains("like gecko") || useragent.contains("trident")) {
			// ie11或者win10中用户代理字符串已经修改了，不再是“msie”了，添加了like Gecko，所以加上like gecko判断
			// 这里的replaceAll 将 +(URLEncoder之后空格变成的)变成空格(%20) , 冒号(%3A) 替换为 空格 (_)
			filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20").replaceAll("%3A", "_");
		} else {
			// firefox会走这里, 将冒号替换为_
			filename = new String(filename.getBytes("utf-8"), "iso-8859-1").replaceAll(":", "_");
		}
		response.setContentType("application/octet-stream");
		// 注意，打上 "" 不然可能文件名不完整 最后 filename都是 查询结果_2019-03-17 10_36_00.csv 这种满意的格式的
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
		OutputStream outputStream = response.getOutputStream();
		CsvWriter writer = null;
		try {
			// 中文一般都是文件格式编码的问题,CSV文件格式默认编码是GBK
			writer = new CsvWriter(outputStream, ',', Charset.forName("GBK"));
			String[] header = { "姓名", "证件号码", "门禁点", "事件类型", "事件时间" };
			String[] line = new String[5];
			writer.writeRecord(header);
			for (BlacklistEvent blacklistEvent : blacklistEvents) {
				line[0] = blacklistEvent.getName();
				line[1] = blacklistEvent.getIDCARD();
				line[2] = blacklistEvent.getHappenAddress();
				line[3] = blacklistEvent.getEventType().toString();
				line[4] = DateUtil.format(blacklistEvent.getHappenTime());
				writer.writeRecord(line);
			}
		} catch (Exception e) {
			log.error("出现异常: " + e);
			throw new HikBlacklistException(Constants.CSV_EXPORT_ERROR_CODE, Constants.CSV_EXPORT_ERROR_MSG);
		} finally {
			writer.flush();
			writer.close();
			outputStream.flush();
			outputStream.close();
		}

	}

}
