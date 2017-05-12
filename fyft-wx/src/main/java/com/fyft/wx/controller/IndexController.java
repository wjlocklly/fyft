package com.fyft.wx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyft.wx.logger.WxLogger;


/**
 *<p>Title: IndexController.java</p>
 *<p>Description: 主页</p>
 *<p>CreateDate: 2017年4月24日</p>
 *@author shen
 *@version v1.0
 */

@RestController
public class IndexController {
	
	private final static Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("test")
	public String name() {
		LOG.info("a info logger...");
		LOG.error("a error logger...");
		return "welcome to the FYFT index page.I'm shen.";
	}
}
