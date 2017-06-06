package com.fyft.wx.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyft.core.util.HttpClientHelper;
import com.fyft.wx.logger.WxLogger;


/**
 *<p>Title: IndexController.java</p>
 *<p>Description: 主页</p>
 *<p>CreateDate: 2017年4月24日</p>
 *@author shen
 *@version v1.0
 */

@RestController
@RequestMapping("/index")
public class IndexController {
	
	private final static Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("/blogList")
	public String getBlogData(HttpServletRequest request){
		String blogUrl = "http://www.javazgs.com/content.json?t=1494604782511";
		Map<String, String> map = null;
		//String result = HttpClientHelper.post(blogUrl, map);
		String result = HttpClientHelper.get(blogUrl, map);
		LOG.info("测试gitPage博客请求......");
		return result;
	}
}
